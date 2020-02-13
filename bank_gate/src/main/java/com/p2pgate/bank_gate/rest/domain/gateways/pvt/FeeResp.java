package com.p2pgate.bank_gate.rest.domain.gateways.pvt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "data"
})
public class FeeResp {

    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("data")
    private Data data;

    @JsonProperty("status")
    public Boolean getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    public String getError(){
        return data.getError_description();
    }

    public Float getFee(){
        return this.data.getReceiverCommission()+this.data.getSenderCommission();
    }

}



