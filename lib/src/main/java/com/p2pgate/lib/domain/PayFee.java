package com.p2pgate.lib.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.p2pgate.lib.rest.validation.PAN;
import com.p2pgate.lib.rest.validation.Sum;

/**
 *
 * Created by OAKutsenko on 04.09.2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayFee {

    @JsonProperty("fromCard")
    @PAN
    private String fromCard;

    private String toCard;

    @JsonProperty("sum")
    @Sum(min=100, max = 100000)
    private Float sum;

    private String rid;

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

    @JsonProperty("sum")
    public Float getSum() {
        return sum;
    }

    @JsonProperty("sum")
    public void setSum(Float sum) {
        this.sum = sum;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
