package com.p2pgate.bank_gate.rest.controller;

import com.p2pgate.bank_gate.config.Messages;
import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.domain.PayFee;
import com.p2pgate.bank_gate.rest.exception.PayApiException;
import com.p2pgate.bank_gate.service.PayService;
import com.p2pgate.lib.rest.validation.CVV;
import com.p2pgate.lib.rest.validation.ExpDate;
import com.p2pgate.lib.rest.validation.PAN;
import com.p2pgate.lib.rest.validation.Sum;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;


@Controller
@Validated
@RequestMapping(path = "/pay")
public class PayController {

    @Value("${page.success}")
    private String successURL;

    @Value("${pay.receiver}")
    private String receiver;

    @Value("${paypage.receiver}")
    private String receiverName;

    @Value("${paypage.agreement}")
    private String agreement;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayService service;

    @Autowired
    Messages messages;


    /* Send  payment */
    @RequestMapping(path = "/request", method = RequestMethod.POST)
    public ResponseEntity<String> payReq(
            @PAN @RequestParam(value = "fromCard") String fromCard,
            @CVV @RequestParam(value = "cvv") String cvv,
            @ExpDate  @RequestParam(value = "exp") String expDate,
            @PAN @RequestParam(value = "toCard", required = false) String toCard,
            @Sum(min=100, max = 100000, message = "Incorrect sum value.") @RequestParam(value = "sum") Float sum,
            @Pattern(regexp = "[15]",message="rid.") @RequestParam(value = "rid", required = false, defaultValue = "1") String rid,
            @NotNull @RequestParam(value = "payid") String payid)
            throws PayApiException {


        PayAccept request = new PayAccept(
                fromCard, cvv, expDate, (toCard == null)?receiver:toCard, sum, rid, payid
        );

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_HTML);
        final String verifyPage = service.acceptPay(request);
        return new ResponseEntity<String>(verifyPage, httpHeaders, HttpStatus.OK);
    }


    /*Pay confirm */
    @RequestMapping(path = "/confirm/{rid}", method = RequestMethod.POST)
    public String payConfirm(@Pattern(regexp = "\\d",message="rid.") @PathVariable String rid, WebRequest request,
                             Model model) throws PayApiException {

        String payId = service.complete(request.getParameter("MD"),request.getParameter("PaRes"),
                rid);

        Map<String,String> parameters = new HashMap<>();
        // Success redirect path with payment ID
        parameters.put("path", successURL+"?payid=" + payId);
        model.addAllAttributes(parameters);
        return "success_pg";
    }

    /* Payment page */
    @RequestMapping(path = "/page", method = RequestMethod.GET)
    public String viewPage(@RequestParam(value = "payid") String payid, Model model,
                           @Sum(min=100, max = 100000) @RequestParam(value = "sum") Float sum,
                           @Size(min = 0, max = 29, message = "txtMsg") @RequestParam(value = "text") String txtMsg ){

        Map<String,String> parameters = new HashMap<>();
        parameters.put("payid", payid);
        parameters.put("receiver", receiverName);
        parameters.put("txtMsg", txtMsg);
        parameters.put("sum", sum+"");
        parameters.put("agreement", agreement);
        model.addAllAttributes(parameters);
        return "invoice";
    }

    /* Get fee for payment page */
    @RequestMapping(path = "/page/fee")
    public ResponseEntity<String> getFee(@RequestBody @Valid PayFee request, BindingResult bindingResult)
            throws PayApiException {

        if (bindingResult.hasErrors())
            throw new PayApiException("0", "101", "Bad JSON request values or format.");

        request.setToCard(receiver);
        request.setRid("1");

        return new ResponseEntity<>(service.getFee(request),  HttpStatus.OK);
    }


    /* SEnd payment from page. */
    @RequestMapping(path = "/page/send")
    public ResponseEntity<String> payment(@RequestBody @Valid PayAccept request, BindingResult bindingResult)
            throws PayApiException {

        if (bindingResult.hasErrors())
            throw new PayApiException("0", "101", "Bad JSON request values or format.");

        request.setToCard(receiver);
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_HTML);
        final String verifyPage = service.acceptPay(request);
        return new ResponseEntity<String>(verifyPage, httpHeaders, HttpStatus.OK);
    }
}
