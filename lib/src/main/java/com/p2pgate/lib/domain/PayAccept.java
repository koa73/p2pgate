package com.p2pgate.lib.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.p2pgate.lib.rest.validation.CVV;
import com.p2pgate.lib.rest.validation.ExpDate;
import com.p2pgate.lib.rest.validation.PAN;
import com.p2pgate.lib.rest.validation.Sum;

import javax.validation.constraints.Pattern;

public class PayAccept {

    @JsonProperty("fromCard")
    @PAN
    private String fromCard;
    private String toCard;
    @JsonProperty("cvv")
    @CVV
    private String cvv;
    @JsonProperty("expDate")
    @ExpDate
    private String expDate;
    @JsonProperty("sum")
    @Sum(min=100, max = 100000)
    private Float sum;
    @JsonProperty("rid")
    @Pattern(regexp = "[15]",message="rid.")
    private String rid;
    @JsonProperty("payid")
    private String payid;

    public PayAccept() {
    }

    public PayAccept(String fromCard, String cvv, String expDate, String toCard, Float sum, String rid, String payid) {
        this.fromCard = fromCard;
        this.cvv = cvv;
        this.expDate = expDate;
        this.toCard = toCard;
        this.sum = sum;
        this.rid = rid;
        this.payid = payid;
    }

    @JsonProperty("fromCard")
    public String getFromCard() {
        return fromCard;
    }

    @JsonProperty("fromCard")
    public void setFromCard(String fromCard) {
        this.fromCard = fromCard;
    }

    public String getToCard() {
        return toCard;
    }

    public void setToCard(String toCard) {
        this.toCard = toCard;
    }

    @JsonProperty("cvv")
    public String getCvv() {
        return cvv;
    }

    @JsonProperty("cvv")
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @JsonProperty("expDate")
    public String getExpDate() {
        return expDate;
    }

    @JsonProperty("expDate")
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @JsonProperty("sum")
    public Float getSum() {
        return sum;
    }

    @JsonProperty("sum")
    public void setSum(Float sum) {
        this.sum = sum;
    }

    @JsonProperty("rid")
    public String getRid() {
        return rid;
    }

    @JsonProperty("rid")
    public void setRid(String rid) {
        this.rid = rid;
    }

    @JsonProperty("payid")
    public String getPayid() {
        return payid;
    }

    @JsonProperty("payid")
    public void setPayid(String payid) {
        this.payid = payid;
    }
}
