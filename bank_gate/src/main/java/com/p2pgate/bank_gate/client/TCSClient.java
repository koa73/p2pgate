package com.p2pgate.bank_gate.client;

/*
Interface to Tinkoff Credit Systems bank public service for P2P money transfer
*/

import com.p2pgate.bank_gate.rest.domain.gateways.tcs.FeeResp;
import com.p2pgate.bank_gate.rest.domain.gateways.tcs.PayResp;
import com.p2pgate.bank_gate.rest.domain.gateways.tcs.SessionId;
import com.p2pgate.bank_gate.rest.domain.gateways.tcs.confirm.PayConfirmResp;
import com.p2pgate.lib.client.config.FeignSimpleConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(
		name="tcs",
		url="${p2p-gateways.tcs.url}",
		configuration = FeignSimpleConfig.class
)

public interface TCSClient {


	@RequestMapping(value = "/v1/payment_commission", method = RequestMethod.GET)
    ResponseEntity<FeeResp> getFee(
            @RequestParam("origin") String origin,
            @RequestParam("payParameters") String payParameters
    );


	@RequestMapping(method = RequestMethod.GET, value = "/v1/pay")
    ResponseEntity<PayResp> sendPay(
            @RequestParam("origin") String origin,
            @RequestParam("sessionid") String sessionid,
            @RequestParam("payParameters") String payParameters
    );


	@RequestMapping(method = RequestMethod.GET, value = "/v1/confirm")
    ResponseEntity<PayConfirmResp> sendConfirm(
            @RequestParam("origin") String origin,
            @RequestParam("sessionid") String sessionid,
            @RequestParam("initialOperationTicket") String initTicket,
            @RequestParam("initialOperation") String operationType,
            @RequestParam("confirmationData") String paRes
    );



	@RequestMapping(method = RequestMethod.GET, value = "/v1/session")
    ResponseEntity<SessionId> getSessionId(
            @RequestParam("origin") String origin
            //@RequestParam("sessionid") String sessionid,
    );
}