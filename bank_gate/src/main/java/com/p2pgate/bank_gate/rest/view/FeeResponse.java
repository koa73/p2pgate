package com.p2pgate.bank_gate.rest.view;

/**
 *
 * Created by OAKutsenko on 11.04.2017.
 */

import com.fasterxml.jackson.annotation.*;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_CEILING;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "fee",
        "total",
        "rid",
        "bank"
})

public class FeeResponse{

    @JsonProperty("fee")
    private String fee;
    @JsonProperty("rid")
    private String rid;
    @JsonProperty("total")
    private String total;
    @JsonProperty("amount")
    private float amount;
    @JsonIgnore
    private String bank;

    public FeeResponse(float fee, float amount){
        setData(fee, amount, fee+amount);
    }


    public FeeResponse(float fee, float amount, float total){
        setData(fee,amount, total);
    }

    private void setData(float fee, float amount, float total ){

        if (fee >0){

            this.fee = new BigDecimal(fee).setScale(2, ROUND_CEILING)+"";
            this.total = new BigDecimal(total).setScale(2, ROUND_CEILING)+"";

        } else {
            this.fee = "0.00";
            this.total = new BigDecimal(amount).setScale(2, ROUND_CEILING)+"";
        }
        this.amount = amount;
    }

    @JsonProperty("fee")
    public String getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(String fee) {
        this.fee = fee;
    }

    @JsonProperty("rid")
    public String getRid() {
        return rid;
    }

    @JsonProperty("rid")
    public void setRid(String rid) {
        this.rid = rid;
    }

    @JsonProperty("total")
    public String getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(String total) {
        this.total = total;
    }

    @JsonProperty("amount")
    public String getAmount() {
        return new BigDecimal(amount).setScale(2, ROUND_CEILING)+"";
    }

    @JsonProperty("amount")
    public void setAmount(float amount) {
        this.amount = amount;
    }

    @JsonAnySetter
    public void setBank(String bank) {
        this.bank = bank;
    }

    @JsonIgnore
    public String getShortJson(){
        return "{"+
                "\"fee\":\""+this.fee+
                "\", \"rid\":\""+this.rid +
                "\", \"amount\":\""+getAmount() +
                "\", \"total\":\""+this.total +
                "\", \"bank\":\""+this.bank +
                "\"}";
    }
}
