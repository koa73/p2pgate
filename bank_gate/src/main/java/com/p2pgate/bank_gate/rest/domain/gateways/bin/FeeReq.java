package com.p2pgate.bank_gate.rest.domain.gateways.bin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.p2pgate.lib.domain.PayFee;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "amount",
        "src_number",
        "tgt_number"
})
public class FeeReq {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("src_number")
    private String srcNumber;
    @JsonProperty("tgt_number")
    private String tgtNumber;


    public FeeReq(PayFee request, String currency){
        this.amount = request.getSum()+"";
        this.currency = currency;
        this.srcNumber = request.getFromCard();
        this.tgtNumber =  request.getToCard();
    }


        @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonProperty("src_number")
    public String getSrcNumber() {
        return srcNumber;
    }

    @JsonProperty("src_number")
    public void setSrcNumber(String srcNumber) {
        this.srcNumber = srcNumber;
    }

    @JsonProperty("tgt_number")
    public String getTgtNumber() {
        return tgtNumber;
    }

    @JsonProperty("tgt_number")
    public void setTgtNumber(String tgtNumber) {
        this.tgtNumber = tgtNumber;
    }
}