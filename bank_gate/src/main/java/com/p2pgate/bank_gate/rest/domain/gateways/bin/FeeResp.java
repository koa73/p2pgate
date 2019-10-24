package com.p2pgate.bank_gate.rest.domain.gateways.bin;

/**
 *
 * Created by OAKutsenko on 07.04.2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fee",
        "min_fee",
        "max_fee",
        "percentage"
})
public class FeeResp {

    @JsonProperty("fee")
    private Float fee;
    @JsonProperty("min_fee")
    private Double minFee;
    @JsonProperty("max_fee")
    private Object maxFee;
    @JsonProperty("percentage")
    private Double percentage;


    @JsonProperty("fee")
    public Float getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(Float fee) {
        this.fee = fee;
    }

    @JsonProperty("min_fee")
    public Double getMinFee() {
        return minFee;
    }

    @JsonProperty("min_fee")
    public void setMinFee(Double minFee) {
        this.minFee = minFee;
    }

    @JsonProperty("max_fee")
    public Object getMaxFee() {
        return maxFee;
    }

    @JsonProperty("max_fee")
    public void setMaxFee(Object maxFee) {
        this.maxFee = maxFee;
    }

    @JsonProperty("percentage")
    public Double getPercentage() {
        return percentage;
    }

    @JsonProperty("percentage")
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
