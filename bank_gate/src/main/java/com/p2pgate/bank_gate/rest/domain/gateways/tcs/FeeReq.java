package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "moneyAmount",
        "provider",
        "cardNumber",
        "providerFields"
})
public class FeeReq {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("moneyAmount")
    private String moneyAmount;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("providerFields")
    private ProviderFields providerFields;

    public  FeeReq(){}

    public FeeReq(String amount,  String fromCard, String toCard){
        this.currency = "RUB";
        this.moneyAmount = amount;
        this.provider = "c2c-anytoany";
        this.cardNumber = fromCard;
        this.providerFields = new ProviderFields(toCard);
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
    public String getMoneyAmount() {
        return moneyAmount;
    }

    @JsonProperty("moneyAmount")
    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @JsonProperty("provider")
    public String getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonProperty("cardNumber")
    public String getCardNumber() {
        return cardNumber;
    }

    @JsonProperty("cardNumber")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

        @JsonProperty("toCardNumber")
        private String toCardNumber;

        public ProviderFields(){}
        public ProviderFields(String toCardNumber){
            this.toCardNumber = toCardNumber;
        }

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

