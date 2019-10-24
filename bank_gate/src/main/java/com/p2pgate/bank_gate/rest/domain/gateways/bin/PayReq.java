package com.p2pgate.bank_gate.rest.domain.gateways.bin;

/**
 * Created by OAKutsenko on 07.04.2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.p2pgate.lib.domain.PayAccept;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "currency",
        "cvc",
        "expire_date",
        "src_number",
        "tgt_number"
})
public class PayReq {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("cvc")
    private String cvc;
    @JsonProperty("expire_date")
    private String expireDate;
    @JsonProperty("src_number")
    private String srcNumber;
    @JsonProperty("tgt_number")
    private String tgtNumber;

    public PayReq(PayAccept request, String currency){
        this.amount = request.getSum().toString();
        this.cvc = request.getCvv();
        this.currency = currency;
        this.srcNumber = request.getFromCard();
        this.tgtNumber = request.getToCard();
        this.expireDate = request.getExpDate();
    }

    public PayReq(){}

    @JsonProperty("cvc")
    public String getCvc() {
        return cvc;
    }

    @JsonProperty("cvc")
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @JsonProperty("expire_date")
    public String getExpireDate() {
        return expireDate;
    }

    @JsonProperty("expire_date")
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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