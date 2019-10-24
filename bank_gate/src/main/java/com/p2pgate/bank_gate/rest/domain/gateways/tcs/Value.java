package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

/**
 *
 * Created by OAKutsenko on 10.04.2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "value"
})
 class Value {

    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("value")
    private Float value;

    @JsonProperty("currency")
    Currency getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @JsonProperty("value")
    public Float getValue() {
        return this.value;
    }

    @JsonProperty("value")
    public void setValue(Float value) {
        this.value = value;
    }
}



