package com.p2pgate.bank_gate.rest.domain.gateways.tcs.confirm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * Created by OAKutsenko on 03.05.2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "paymentId",
        "paymentHash"
})
public class Payload {

    @JsonProperty("paymentId")
    private String paymentId;
    @JsonProperty("paymentHash")
    private String paymentHash;

    @JsonProperty("paymentId")
    String getPaymentId() {
        return paymentId;
    }

    @JsonProperty("paymentId")
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @JsonProperty("paymentHash")
    public String getPaymentHash() {
        return paymentHash;
    }

    @JsonProperty("paymentHash")
    public void setPaymentHash(String paymentHash) {
        this.paymentHash = paymentHash;
    }
}
