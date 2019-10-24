/**
 *
 * Created by OAKutsenko on 24.10.2017.
 */

$(document).ready(function(){
    "use strict";
    
    var requestMode, formValue;

    var responseFunction = function(data, status, xhr){

        var ct = xhr.getResponseHeader("content-type") || "";

        if (ct.indexOf('html') > -1) {

            if (requestMode =='send'){
                $("body").html(data);
                $('form').submit();
            }
            
        } else if (ct.indexOf('json') > -1) {

            switch(requestMode) {

                case 'fee':

                    toggleSubmitButton("span.toggleButton");
                    formValue = addData(data);
                    $("#bank").html(data.bank);
                    $("#amount").html(data.amount);
                    $("#total").html(data.total);
                    $("#feeValue").html(data.fee);
                    $("#sum_i").hide();
                    $("#sum_v").show();
                    break;

                default:

                    break;
            }
        } 
    };

    var  showErrorView = function (xhr, ajaxOptions, thrownError) {

        var ct = xhr.getResponseHeader("content-type") || "";

        if (ct.indexOf('html') > -1) {

            $("html").html(xhr.responseText);

        } else if (ct.indexOf('json') > -1) {

            var err = eval("(" + xhr.responseText + ")");

            $("#result").removeClass("hidden");
            $("#invoice").addClass("hidden");
            $("#errorHeader").removeClass("hidden");
            $("#title").html(xhr?(xhr.status>0?xhr.status:522):'400');

            if(err.message){
                $("#msg").html(err.message);
            } else  if (thrownError) {
                $("#msg").html(thrownError);
            }
            //console.log("Status: " + xhr.status + " , Error: " + thrownError + " , " + err.message);
        }
    };


    $("input[type=checkbox]").click(function () {

        if ( $(this).prop('checked') == true ){

            $("#agree").removeAttr("style");

        } else {

            $("#agree").css('color','#f44336');
        }
    });


    setFormValidation('#InvoiceAccept');

    $("#pan").mask('M000-0000-0000-0000',{
        translation: {
            'M': {
                pattern: /[2,4,5]/, optional: false
            }
        }
    });

    $("#cvv").mask('000');

    $("#suminp").mask('0####.00', {
        reverse: true,
        onChange: function(cep){
            $("#suminp").val(cep.replace(/^0/,''));
        }
    });

    $("#expDate").mask('M0/Y0',{
        translation: {
            'M': {
                pattern: /[0,1]/, optional: false
            },
            'm': {
                pattern: /[1-9]/, optional: false
            },
            's': {
                pattern: /[0,1,2]/, optional: false
            },
            'Y':{
                pattern: /[1-2]/, optional: false
            }
        },
        onKeyPress: function(cep, e, field, options) {
            var mask = 'Mm/Y0';
            if (cep == 1 || cep >9){
                mask = 'Ms/Y0';
            }
            $('#expDate').mask(mask, options);
        }
    });


    $.validator.addMethod( "dataOld", function( value, element) {
        if ( this.optional( element ) ) {
            return true;
        }
        return expDateValidate(value);
    }, "Invalid data is old." );

    $.validator.addMethod( "agreeCheck", function( value, element) {
        if (element.checked){
            $(element).closest( "div").removeClass('has-error');
            return true;
        }
        return false;
    }, "Please accept our policy." );
    
});

<!-- Validate if entered date in the past -->
function expDateValidate(cep){
    var now = new Date();
    var year = now.getUTCFullYear();
    var month = ("0" +(now.getUTCMonth() + 1)).slice(-2);
    var entered = cep.replace(/(\d{2})\/(\d{2})/,'20$2$1');
    return (entered >=(year+""+month));
}

// Form validation
function setFormValidation(id){
    $(id).validate({
        rules: {
            pan: {
                required: true,
                creditcard: true,
                minlength: 19
            },
            sum: {
                required: true,
                min: 100
            },
            cvv:{
                required: true,
                minlength: 3
            },
            expDate: {
                required: true,
                minlength: 5,
                dataOld: true
            },
            agree:{
                required: true,
                agreeCheck: true
            }
        },
        errorPlacement: function(error, element) {
            $(element).parent('div').addClass('has-error');

            if($(element).is(':checkbox')){
                $("#agree").css('color','#f44336');
            }
        },
        submitHandler: function(form) {
            findPressedButton(form);
            switch(requestMode) {
                case 'fee': // get fee
                    formValue = getFormData(form);
                    ajaxReq(requestMode, formValue);
                    break;

                default:  // pay
                    ajaxReq(requestMode, formValue);
                    break;
            }
        }
    });
}

// Get which submit is showing and was pressed
function findPressedButton(element){
    $(element).find('button[type="submit"]').each(function( index ) {
        if (!$( this ).hasClass("hidden")) {
            requestMode = this.id;
        }
    });
}

<!-- Get form value as JSON -->
function getFormData(fomName){
    var paramObj = {};
    $.each($(fomName).find('input:not(:checkbox)').serializeArray(), function(_, kv) {
        if (paramObj.hasOwnProperty(kv.name)) {
            paramObj[kv.name] = $.makeArray(paramObj[kv.name]);
            paramObj[kv.name].push(kv.value.replace(/[/-]/g,''));
        }
        else {
            paramObj[kv.name] = kv.value.replace(/[/-]/g,'');
        }
    });

    paramObj['text'] = getParameterByName('text');
    return JSON.stringify(paramObj);
}

<!-- Switch buttons view -->
function toggleSubmitButton(span){
    $(span).find('button').each(function( index ) {
        if ($( this ).hasClass("hidden")){
            $( this ).removeClass("hidden");
        } else {
            $( this ).addClass("hidden");
        }
    });
}

// Add data to json
function addData($data) {
    if($data.rid) {
        return formValue.replace('}', ',"rid":"' + $data.rid + '", "fee":"'+$data.fee+'"}');
    }
}

// Get parameter fom URL
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function ajaxReq ($url, $parameters){
    $.ajax({
        url: window.location.origin + window.location.pathname + "/"+$url,
        type: 'POST',
        data: $parameters, //JSON.stringify($parameters)
        async: true,
        beforeSend: function (xhr) {

            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");

            $('input').prop('disabled', true);
            $('button').prop('disabled', true);
            $("#loader").show();
        },
        complete: function () {

            $("#loader").delay(100).slideUp(100);
            $('button').prop('disabled', false);

        },
        success: responseFunction,
        error: showErrorView
    });
}