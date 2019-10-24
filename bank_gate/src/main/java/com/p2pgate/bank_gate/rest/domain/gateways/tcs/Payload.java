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
        "maxAmount",
        "total",
        "providerId",
        "minAmount",
        "limit",
        "value"
})
public class Payload {

    @JsonProperty("maxAmount")
    private Integer maxAmount;
    @JsonProperty("total")
    private Total total;
    @JsonProperty("providerId")
    private String providerId;
    @JsonProperty("minAmount")
    private Integer minAmount;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("value")
    private Value value;

    @JsonProperty("maxAmount")
    public Integer getMaxAmount() {
        return maxAmount;
    }

    @JsonProperty("maxAmount")
    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    @JsonProperty("total")
    public Total getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Total total) {
        this.total = total;
    }

    @JsonProperty("providerId")
    public String getProviderId() {
        return providerId;
    }

    @JsonProperty("providerId")
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @JsonProperty("minAmount")
    public Integer getMinAmount() {
        return minAmount;
    }

    @JsonProperty("minAmount")
    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("value")
    public Value getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Value value) {
        this.value = value;
    }
}