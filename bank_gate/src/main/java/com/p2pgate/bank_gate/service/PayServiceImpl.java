package com.p2pgate.bank_gate.service;

import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.domain.PayFee;
import com.p2pgate.bank_gate.rest.exception.PayApiException;
import com.p2pgate.bank_gate.rest.view.FeeResponse;
import com.p2pgate.bank_gate.service.gateways.GateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PayServiceImpl implements PayService {


    @Autowired
    @Qualifier("tcs")
    GateService tcsService;

    @Autowired
    @Qualifier("bin")
    GateService binService;

    @Value("${callback.pay}")
    private String payCallback;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String getFee(PayFee request) throws PayApiException {

        GateService client = getRouteClient(request.getRid());

        FeeResponse response = client.getFee(request);


        if (response == null){

            log.error("Destination route not available. "+client.getClass().getName()+"  <<---\n" );
            response = tcsService.getFee(request);  /* --------- Choose  default channel  */

            if (response == null)
                throw new PayApiException("0", "111", "Service temporarily unavailable.");

            response.setRid("1");               /* --------- Set default channel id */

        } else {
            response.setRid(request.getRid());
        }

        switch (request.getRid()){

            case "1":
                response.setBank("TCS Bank");
                break;

            case "5":
                response.setBank("BIN Bank");
                break;

            default:
                response.setBank("TCS Bank");
        }

        return response.getShortJson();
    }

    @Override
    public String acceptPay(PayAccept request) throws PayApiException {

        return getRouteClient(request.getRid()).sendPay(request, payCallback);
    }

    @Override
    public String complete(String md, String PaRes, String rid) throws PayApiException {

        return getRouteClient(rid).sendConfirm(md, PaRes);
    }


    private GateService getRouteClient(String routeId){

        switch(routeId)

        {
            case "1":    /** TCS Bank */
                return tcsService;

            case "5":    /** BIN&MDM */
                return binService;

            default:
                return tcsService;
        }
    }
}
