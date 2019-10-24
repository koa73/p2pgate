package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "paymentId",
        "url",
        "confirmationType",
        "merchantData",
        "requestSecretCode"
})
public class TDSecure {

    @JsonProperty("paymentId")
    private String paymentId;
    @JsonProperty("url")
    private String url;
    @JsonProperty("confirmationType")
    private String confirmationType;
    @JsonProperty("merchantData")
    private String merchantData;
    @JsonProperty("requestSecretCode")
    private String requestSecretCode;

    @JsonProperty("paymentId")
    public String getPaymentId() {
        return paymentId;
    }

    @JsonProperty("paymentId")
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("confirmationType")
    public String getConfirmationType() {
        return confirmationType;
    }

    @JsonProperty("confirmationType")
    public void setConfirmationType(String confirmationType) {
        this.confirmationType = confirmationType;
    }

    @JsonProperty("merchantData")
    public String getMerchantData() {
        return merchantData;
    }

    @JsonProperty("merchantData")
    public void setMerchantData(String merchantData) {
        this.merchantData = merchantData;
    }

    @JsonProperty("requestSecretCode")
    public String getRequestSecretCode() {
        return requestSecretCode;
    }

    @JsonProperty("requestSecretCode")
    public void setRequestSecretCode(String requestSecretCode) {
        this.requestSecretCode = requestSecretCode;
    }

}
