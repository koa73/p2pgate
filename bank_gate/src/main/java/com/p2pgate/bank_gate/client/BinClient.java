package com.p2pgate.bank_gate.client;

/*
Interface to BIN bank public service for P2P money transfer
*/

import com.p2pgate.bank_gate.rest.domain.gateways.bin.*;
import com.p2pgate.lib.client.config.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
		name="bin",
		url="${p2p-gateways.bin.url}",
		configuration = FeignConfig.class
)

public interface BinClient {

	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/fee",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<FeeResp> getFee(FeeReq request);


	@RequestMapping(method = RequestMethod.POST,  value = "/api/v1/requests",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<PayResp> sendPay(PayReq request);

	@RequestMapping(method = RequestMethod.POST,  value = "/api/v1/requests/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<PayConfirmResp> sendConfirm(@PathVariable("id") String id,
											   @RequestHeader("Authorization") String credentials, PayConfirmReq request);

	@RequestMapping(method = RequestMethod.GET,  value = "/api/v1/requests/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<PayStateResp> getState(@PathVariable("id") String id, @RequestHeader("Authorization") String credentials);
}