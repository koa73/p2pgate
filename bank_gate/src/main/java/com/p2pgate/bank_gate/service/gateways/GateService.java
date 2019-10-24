package com.p2pgate.bank_gate.service.gateways;


import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.domain.PayFee;
import com.p2pgate.bank_gate.rest.exception.PayApiException;
import com.p2pgate.bank_gate.rest.view.FeeResponse;


/**
 *
 * Created by Олег on 20.09.2019.
 */
public interface GateService {

    FeeResponse getFee(PayFee request) throws PayApiException;
    String sendPay(PayAccept request, String payCallback) throws PayApiException;
    String sendConfirm(String md, String PaRes) throws PayApiException;
}
