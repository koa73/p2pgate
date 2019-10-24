package com.p2pgate.bank_gate.rest.domain.gateways.bin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "md",
        "pa_res",
        "request_id"
})
public class PayConfirmReq {

    @JsonProperty("md")
    private String md;
    @JsonProperty("pa_res")
    private String paRes;
    @JsonProperty("request_id")
    private String requestId;


    @JsonIgnore
    public PayConfirmReq(String md, String PaRes){

        this.md = md;
        this.paRes = PaRes;
    }

    @JsonProperty("md")
    public String getMd() {
        return md;
    }

    @JsonProperty("md")
    public void setMd(String md) {
        this.md = md;
    }

    @JsonProperty("pa_res")
    public String getPaRes() {
        return paRes;
    }

    @JsonProperty("pa_res")
    public void setPaRes(String paRes) {
        this.paRes = paRes;
    }

    @JsonProperty("request_id")
    public String getRequestId() {
        return requestId;
    }

    @JsonProperty("request_id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}