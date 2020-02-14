package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cardNumber",
        "formProcessingTime",
        "securityCode",
        "expiryDate",
        "attachCard",
        "provider",
        "currency",
        "moneyAmount",
        "moneyCommission",
        "providerFields"
})
public class PayReq {

    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("formProcessingTime")
    private String formProcessingTime;
    @JsonProperty("securityCode")
    private String securityCode;
    @JsonProperty("expiryDate")
    private String expiryDate;
    @JsonProperty("attachCard")
    private String attachCard;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("moneyAmount")
    private String moneyAmount;
    @JsonProperty("moneyCommission")
    private String moneyCommission;
    @JsonProperty("providerFields")
    private ProviderFields providerFields;

    public PayReq(){}

    public PayReq(String fromCard, String cvv, String expiryDate, String  amount, String toCard){

        this.cardNumber = fromCard;
        this.currency = "RUB";
        this.expiryDate = expiryDate;
        this.securityCode = "000";
        this.formProcessingTime = cvv;
        this.moneyAmount = amount;
        this.moneyCommission = amount;
        this.attachCard = "false";
        this.provider = "c2c-anytoany";
        this.providerFields = new ProviderFields(toCard);
    }

    @JsonProperty("cardNumber")
    public String getCardNumber() {
        return cardNumber;
    }

    @JsonProperty("cardNumber")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @JsonProperty("formProcessingTime")
    public String getFormProcessingTime() {
        return formProcessingTime;
    }

    @JsonProperty("formProcessingTime")
    public void setFormProcessingTime(String formProcessingTime) {
        this.formProcessingTime = formProcessingTime;
    }

    @JsonProperty("securityCode")
    public String getSecurityCode() {
        return securityCode;
    }

    @JsonProperty("securityCode")
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @JsonProperty("expiryDate")
    public String getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("expiryDate")
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @JsonProperty("attachCard")
    public String getAttachCard() {
        return attachCard;
    }

    @JsonProperty("attachCard")
    public void setAttachCard(String attachCard) {
        this.attachCard = attachCard;
    }

    @JsonProperty("provider")
    public String getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("moneyAmount")
    public String  getMoneyAmount() {
        return moneyAmount;
    }

    @JsonProperty("moneyAmount")
    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @JsonProperty("moneyCommission")
    public String getMoneyCommission() {
        return moneyCommission;
    }

    @JsonProperty("moneyCommission")
    public void setMoneyCommission(String moneyCommission) {
        this.moneyCommission = moneyCommission;
    }

    @JsonProperty("providerFields")
    public ProviderFields getProviderFields() {
        return providerFields;
    }

    @JsonProperty("providerFields")
    public void setProviderFields(ProviderFields providerFields) {
        this.providerFields = providerFields;
    }


    @JsonIgnore
    @Override
    public String toString() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e){

            return null;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "toCardNumber"
    })
   class ProviderFields {

        public ProviderFields(){}
        public ProviderFields(String toCardNumber){
            this.toCardNumber = toCardNumber;
        }

        @JsonProperty("toCardNumber")
        private String toCardNumber;


        @JsonProperty("toCardNumber")
        public String getToCardNumber() {
            return toCardNumber;
        }

        @JsonProperty("toCardNumber")
        public void setToCardNumber(String toCardNumber) {
            this.toCardNumber = toCardNumber;
        }
    }
}


