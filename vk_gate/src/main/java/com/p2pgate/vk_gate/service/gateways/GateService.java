package com.p2pgate.vk_gate.service.gateways;

import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.vk_gate.rest.exception.PayApiException;


/**
 *
 * Created by Олег on 20.09.2019.
 */
public interface GateService {


    String refillWalletConfirm(String md, String PaRes, String payment_id) throws PayApiException;
    String refillWallet(PayAccept request) throws PayApiException;
}
