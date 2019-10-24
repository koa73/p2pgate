package com.p2pgate.vk_gate.service;

import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.vk_gate.rest.exception.PayApiException;
import com.p2pgate.vk_gate.service.gateways.GateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class PayServiceImpl implements PayService {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    @Qualifier("vk")
    GateService vkService;


    @Override
    public String acceptPay(PayAccept request) throws PayApiException {

        return vkService.refillWallet(request);
    }

    @Override
    public String complete(String md, String PaRes, String payment_id) throws PayApiException {
        return vkService.refillWalletConfirm(md, PaRes, payment_id);
    }
}
