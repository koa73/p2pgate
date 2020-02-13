package com.p2pgate.bank_gate.rest.domain.gateways.pvt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    @JsonProperty("authorization")
    private String authorization;
    @JsonProperty("redirectUrl")
    private String redirectUrl;
    @JsonProperty("senderCommission")
    private Float senderCommission;
    @JsonProperty("receiverCommission")
    private Float receiverCommission;
    @JsonProperty("error_description")
    private String error_description;

    @JsonProperty("senderCommission")
    public Float getSenderCommission() {
        return senderCommission;
    }

    @JsonProperty("senderCommission")
    public void setSenderCommission(Float senderCommission) {
        this.senderCommission = senderCommission;
    }

    @JsonProperty("receiverCommission")
    public Float getReceiverCommission() {
        return receiverCommission;
    }

    @JsonProperty("receiverCommission")
    public void setReceiverCommission(Float receiverCommission) {
        this.receiverCommission = receiverCommission;
    }

    @JsonProperty("error_description")
    public String getError_description() {
        return error_description;
    }

    @JsonProperty("error_description")
    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @JsonProperty("authorization")
    public String getAuthorization() {
        return authorization;
    }

    @JsonProperty("authorization")
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @JsonProperty("redirectUrl")
    public String getRedirectUrl() {
        return redirectUrl;
    }

    @JsonProperty("redirectUrl")
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
