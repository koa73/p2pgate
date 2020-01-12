package com.p2pgate.bank_gate.service.gateways;


import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.domain.PayFee;
import com.p2pgate.bank_gate.rest.exception.PayApiException;
import com.p2pgate.bank_gate.rest.view.FeeResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 *
 * Created by Олег on 20.09.2019.
 */
public interface GateService {

    FeeResponse getFee(PayFee request) throws PayApiException;
    String sendPay(PayAccept request, String payCallback) throws PayApiException;
    String sendConfirm(String md, String PaRes) throws PayApiException;

    default float getSumWithoutFee(float sum, float minFee, float feePercent){
        float fee, tmp = sum ;

        do {
            fee = Math.max(minFee,(tmp * feePercent/100));
            tmp = sum - fee;
            if (fee == minFee)
                break;

        } while (Math.abs(tmp*(feePercent/100+1) -sum)>.01f);

        if ((tmp*(feePercent/100+1) -sum) >0)
            tmp -=.01f;

        return new BigDecimal(tmp).setScale(2, RoundingMode.HALF_EVEN).floatValue();
    }
}
