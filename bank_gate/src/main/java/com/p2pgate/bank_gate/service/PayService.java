package com.p2pgate.bank_gate.service;


import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.domain.PayFee;
import com.p2pgate.bank_gate.rest.exception.PayApiException;

/**
 *
 * Created by OAKutsenko on 20.09.2019.
 */
public interface PayService {

    String getFee(PayFee request)throws PayApiException;
    String acceptPay(PayAccept request)throws PayApiException;
    String complete(String md, String PaRes, String rid) throws PayApiException;
}