package com.p2pgate.bank_gate.service.gateways;

import com.p2pgate.bank_gate.client.TCSClient;
import com.p2pgate.bank_gate.service.security.JwtService;
import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.lib.domain.PayFee;
import com.p2pgate.bank_gate.rest.domain.gateways.tcs.confirm.PayConfirmResp;
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
import com.p2pgate.bank_gate.rest.domain.gateways.tcs.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 *
 * Created by OAKutsenko on 21.09.2019.
 */

@Service
@Qualifier("tcs")
public class TCSServiceImpl implements GateService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private TCSClient client;

    @Autowired(required = false)
    private RedisRepository redisCache;

    @Autowired
    private JwtService jwt;

    @Override
    public String sendPay(PayAccept request, String payCallback) throws PayApiException {

        try {
            final String sessionId = getSessionId("");

            PayReq req = new PayReq(
                    request.getFromCard(),
                    request.getCvv(),
                    request.getExpDate().replaceAll("(\\d{2})(\\d{2})", "$1/$2"),
                    request.getSum().toString(),
                    request.getToCard()
            );

            ResponseEntity<PayResp> response = client.sendPay(
                    "web,ib5,platform",
                    sessionId,
                    req.toString()
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody().getResultCode().equals("WAITING_CONFIRMATION")){

                TempData sessionData = new TempData(response.getBody().getMD());
                sessionData.setPayId(request.getPayid());
                sessionData.setAdditionalProperty("sessionId", sessionId);
                sessionData.setAdditionalProperty("sum", request.getSum().toString());
                sessionData.setAdditionalProperty("payid", request.getPayid());
                sessionData.setAdditionalProperty("ticket", response.getBody().getOperationTicket());

                redisCache.save(sessionData);

                return response.getBody().getPage(payCallback);

            } else {

                log.error("Reason: " + response.getBody().getPlainMessage());
                throw new PayApiException(request.getPayid(), getErrorId(response.getBody().getPlainMessage())+"",
                        response.getBody().getPlainMessage());

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

            ResponseEntity<PayConfirmResp> response = client.sendConfirm(
                    "web,ib5,platform",
                    sessionData.getAdditionalProperties().get("sessionId"),
                    sessionData.getAdditionalProperties().get("ticket"),
                    "pay",
                    new PayConfirmReq(PaRes).toString()
            );

            // Check processing response
            if (response.getStatusCode() == HttpStatus.OK && response.getBody().getResultCode().equals("OK")) {

                return jwt.getJwtToken(sessionData.getAdditionalProperties().get("payid"),
                        sessionData.getAdditionalProperties().get("sum"), response.getBody().getPaymentId());
            }

            throw new PayApiException(sessionData.getPayId(), "129", response.getBody().getErrorMessage());

        } else {

            throw new PayApiException("0", "121", "Cache record not found.");
        }
    }

    @Override
    public FeeResponse getFee(PayFee request) throws PayApiException {

        try {

            FeeReq req = new FeeReq(
                    request.getSum().toString(),
                    request.getFromCard(),
                    request.getToCard());


            ResponseEntity<FeeResp> response = client.getFee(
                    "web,ib5,platform", req.toString());

            if (response.getStatusCode()== HttpStatus.OK && response.getBody().getResultCode().equals("OK")){


                return new FeeResponse(response.getBody().getFee(), request.getSum(),
                        response.getBody().getTotal());
            } else {

                throw new PayApiException("0", "128", response.getBody().getErrorMessage());
            }

        } catch (Exception e){

            throw new PayApiException("0", "122", "Bank request error.");
        }
    }

    private String getSessionId(String sessionId){

        ResponseEntity<SessionId> sessionResp = client.getSessionId("web,ib5,platform");
        return sessionResp.getBody().getPayload();
    }


    /* Translate received error messages */
    private int getErrorId(String errorReason){

        if (errorReason.contains("Правильный код подтверждения не был получен."))
            return 124;
        if(errorReason.contains("Недостаточно денежных средств"))
            return 123;
        if (errorReason.contains("Операция отклонена банком."))
            return 122;
        if (errorReason.contains("карты отправителя"))
            return 127;
        if (errorReason.contains("3DSecure"))
            return 125;

        return 121;
    }
}
