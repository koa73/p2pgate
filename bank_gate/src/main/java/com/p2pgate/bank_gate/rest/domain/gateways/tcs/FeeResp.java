package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

/**
 *
 * Created by OAKutsenko on 10.04.2017.
 */
import com.fasterxml.jackson.annotation.JsonProperty;


public class FeeResp {

    @JsonProperty("resultCode")
    private String resultCode;
    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("resultCode")
    public String getResultCode() {
        return resultCode;
    }

    @JsonProperty("resultCode")
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @JsonProperty("payload")
    private Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @JsonProperty("errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCurrency(){
        return this.getPayload().getValue().getCurrency().getName();
    }

    public int getCurrencyCode(){
        return this.getPayload().getValue().getCurrency().getCode();
    }

    public Float getFee() {
        return this.getPayload().getValue().getValue();
    }

    public Float getTotal() {
        return this.getPayload().getTotal().getValue();
    }
}