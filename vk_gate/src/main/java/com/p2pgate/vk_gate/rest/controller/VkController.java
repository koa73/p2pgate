package com.p2pgate.vk_gate.rest.controller;

import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.rest.validation.CVV;
import com.p2pgate.lib.rest.validation.ExpDate;
import com.p2pgate.lib.rest.validation.PAN;
import com.p2pgate.lib.rest.validation.Sum;
import com.p2pgate.vk_gate.rest.exception.PayApiException;
import com.p2pgate.vk_gate.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@Controller
@Validated
@RequestMapping(path = "/vk-transfer")
public class VkController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${test.pan}")
    private String pan;

    @Value("${test.cvv}")
    private String cvv;

    @Value("${test.exp}")
    private String exp;

    @Value("${userenv.username}")
    private String username;

    @Autowired
    private PayService service;

    /* Send  payment */
    @RequestMapping(path = "/request", method = RequestMethod.POST)
    public ResponseEntity<String> payReq(
            @PAN @RequestParam(value = "fromCard") String fromCard,
            @CVV @RequestParam(value = "cvv") String cvv,
            @ExpDate  @RequestParam(value = "exp") String expDate,
            @Sum(min=1, max = 15000, message = "Incorrect sum value.") @RequestParam(value = "sum") Float sum,
            @Pattern(regexp = "[1]",message="rid.") @RequestParam(value = "rid", required = false, defaultValue = "1") String rid)
            throws PayApiException {

        PayAccept request = new PayAccept(
                fromCard, cvv, expDate, null, sum, rid, null
        );

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_HTML);
        final String verifyPage = service.acceptPay(request);
        return new ResponseEntity<String>(verifyPage, httpHeaders, HttpStatus.OK);
    }



    /*Pay confirm */
    @RequestMapping(path = "/confirm/{payment_id}", method = RequestMethod.POST)
    public String payConfirm(@PathVariable String payment_id, WebRequest request, Model model) throws PayApiException {

       String amount =  service.complete(request.getParameter("MD"),request.getParameter("PaRes"), payment_id);
       if(amount != null){

           Map<String,String> parameters = new HashMap<>();
           parameters.put("receiver", "VK Wallet");
           parameters.put("user", username);
           parameters.put("amount", amount);
           model.addAllAttributes(parameters);

           return "success";
        }
        throw  new PayApiException(payment_id, 406, " Transaction complete error.");
    }


    /* Show test page */
    @RequestMapping(path = "/page", method = RequestMethod.GET)
    public String viewPage(Model model){

        //
        Map<String,String> parameters = new HashMap<>();
        parameters.put("pan", pan);
        parameters.put("cvv", cvv);
        parameters.put("exp", exp);

        return "test_page";
    }
}
