package com.p2pgate.bank_gate.service.gateways;

import com.p2pgate.bank_gate.client.BinClient;
import com.p2pgate.bank_gate.service.security.JwtService;
import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.domain.PayFee;
import com.p2pgate.bank_gate.rest.domain.gateways.bin.*;
import com.p2pgate.bank_gate.rest.exception.PayApiException;
import com.p2pgate.bank_gate.rest.view.FeeResponse;
import com.p2pgate.lib.redis.domain.TempData;
import com.p2pgate.lib.redis.repository.RedisRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 *
 * Created by OAKutsenko on 21.09.2019.
 */

@Service
@Qualifier("bin")
public class BinServiceImpl implements GateService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private BinClient binClient;

    @Autowired(required = false)
    private RedisRepository redisCache;

    @Autowired
    private JwtService jwt;


    @Override
    public FeeResponse getFee(PayFee request) throws PayApiException {

        ResponseEntity<FeeResp> response = binClient.getFee(new FeeReq(request, "RUR"));

        if (response.getStatusCode()== HttpStatus.OK && response.getBody().getFee() != null){

            return new FeeResponse(response.getBody().getFee(), request.getSum());
        }
        return null;
    }

    @Override
    public String sendPay(PayAccept request, String payCallback) throws PayApiException {
        try {

            ResponseEntity<PayResp> response = binClient.sendPay(new PayReq(request, "RUR"));

            if (response.getStatusCode() == HttpStatus.OK && !response.getBody().getState().equals("error") &&
                    response.getBody().getTdsData() != null) {

                TempData sessionData = new TempData(response.getBody().getMD());
                sessionData.setAdditionalProperty("token", response.getBody().getAccessToken());
                sessionData.setAdditionalProperty("sum", request.getSum().toString());
                sessionData.setAdditionalProperty("payid", request.getPayid());
                sessionData.setAdditionalProperty("request_id", response.getBody().getRequestId());
                sessionData.setPayId(request.getPayid());

                redisCache.save(sessionData);

                return response.getBody().getPage(payCallback);


            } else {

                log.error("Reason: BinBank send payment error.");
                throw new PayApiException(request.getPayid(), "119", "3DSec authorization data access error.");
            }

        } catch (FeignException e){

            log.error("Processing service unavailable. routeId: "+request.getRid());
            throw new PayApiException(request.getPayid(), "119", "Processing unreachable.");

        } catch (JedisConnectionException e){

            log.error("Redis connection error.");
            throw new PayApiException(request.getPayid(), "119", "Redis connection error.");

        }
    }

    @Override
    public String sendConfirm(String md, String PaRes) throws PayApiException {

        TempData sessionData = redisCache.findById(md);
        if (sessionData != null){

            ResponseEntity<PayConfirmResp> confirmResp = binClient.sendConfirm(
                    sessionData.getAdditionalProperties().get("request_id"),
                    "Bearer "+sessionData.getAdditionalProperties().get("token"),
                    new PayConfirmReq(md, PaRes)
            );

            if (confirmResp.getStatusCode()== HttpStatus.OK && confirmResp.getBody().getState().equals("ok")){

                ResponseEntity<PayStateResp> stateResp = binClient.getState(
                        sessionData.getAdditionalProperties().get("request_id"),
                        "Bearer "+sessionData.getAdditionalProperties().get("token")
                );

                if (stateResp.getStatusCode()== HttpStatus.OK){

                    if (!stateResp.getBody().getStateId().equals("error")) {

                        return jwt.getJwtToken(sessionData.getAdditionalProperties().get("payid"),
                                sessionData.getAdditionalProperties().get("sum"), stateResp.getBody().getRrn());

                    } else {

                        throw new PayApiException(sessionData.getPayId(), "122", "Operation complete error.");
                    }
                } else {
                    throw new PayApiException(sessionData.getPayId(), "121", "Operation aborted unknown error.");
                }
            } else {
                throw new PayApiException(sessionData.getPayId(), "119", "Processing access error. Service not responded.");
            }

        } else {
            log.error("Cache record not found.");
            throw new PayApiException("0", "121", "Cache record not found.");
        }
    }
}
