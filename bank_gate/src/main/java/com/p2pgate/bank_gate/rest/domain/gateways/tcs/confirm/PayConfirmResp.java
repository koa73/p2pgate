package com.p2pgate.bank_gate.rest.domain.gateways.tcs.confirm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "resultCode",
        "payload",
        "plainMessage",
        "trackingId",
        "isBusinessError"
})
public class PayConfirmResp {

    @JsonProperty("resultCode")
    private String resultCode;
    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("errorMessage")
    private String errorMessage;
    @JsonProperty("plainMessage")
    private String plainMessage;
    @JsonProperty("trackingId")
    private String trackingId;
    @JsonProperty("isBusinessError")
    private Boolean isBusinessError;

    @JsonProperty("resultCode")
    public String getResultCode() {
        return resultCode;
    }

    @JsonProperty("resultCode")
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @JsonProperty("errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("plainMessage")
    public String getPlainMessage() {
        return plainMessage;
    }

    @JsonProperty("plainMessage")
    public void setPlainMessage(String plainMessage) {
        this.plainMessage = plainMessage;
    }

    @JsonProperty("trackingId")
    public String getTrackingId() {
        return trackingId;
    }

    @JsonProperty("trackingId")
    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    @JsonProperty("isBusinessError")
    public Boolean getIsBusinessError() {
        return isBusinessError;
    }

    @JsonProperty("isBusinessError")
    public void setIsBusinessError(Boolean isBusinessError) {
        this.isBusinessError = isBusinessError;
    }

    @JsonProperty("payload")
    private Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getPaymentId(){
        return this.getPayload().getPaymentId();
    }

    public void setPaymentId(String paymentId){

        this.payload.setPaymentId(paymentId);
    }
}