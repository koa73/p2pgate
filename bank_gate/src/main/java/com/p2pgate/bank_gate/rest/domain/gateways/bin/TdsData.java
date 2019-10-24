package com.p2pgate.bank_gate.rest.domain.gateways.bin;

/**
 * Created by OAKutsenko on 07.04.2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "acs_url",
        "pa_req",
        "md",
        "term_url"
})
public class TdsData {

    @JsonProperty("acs_url")
    private String acsUrl;
    @JsonProperty("pa_req")
    private String paReq;
    @JsonProperty("md")
    private String md;
    @JsonProperty("term_url")
    private String termUrl;

    @JsonProperty("acs_url")
    public String getAcsUrl() {
        return acsUrl;
    }

    @JsonProperty("acs_url")
    public void setAcsUrl(String acsUrl) {
        this.acsUrl = acsUrl;
    }

    @JsonProperty("pa_req")
    public String getPaReq() {
        return paReq;
    }

    @JsonProperty("pa_req")
    public void setPaReq(String paReq) {
        this.paReq = paReq;
    }

    @JsonProperty("md")
    public String getMd() {
        return md;
    }

    @JsonProperty("md")
    public void setMd(String md) {
        this.md = md;
    }

    @JsonProperty("term_url")
    public String getTermUrl() {
        return termUrl;
    }

    @JsonProperty("term_url")
    public void setTermUrl(String termUrl) {
        this.termUrl = termUrl;
    }

}
