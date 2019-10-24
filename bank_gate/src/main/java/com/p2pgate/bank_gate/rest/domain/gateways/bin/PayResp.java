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
        "access_token",
        "request_id",
        "tds_data",
        "state"
})
public class PayResp {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("tds_data")
    private TdsData tdsData;
    @JsonProperty("state")
    private String state;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("request_id")
    public String getRequestId() {
        return requestId;
    }

    @JsonProperty("request_id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("tds_data")
    public TdsData getTdsData() {
        return tdsData;
    }

    @JsonProperty("tds_data")
    public void setTdsData(TdsData tdsData) {
        this.tdsData = tdsData;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }


    public String getPage(String callBack){

        return  "<html>" +
                "<body OnLoad=\"OnLoadEvent();\">" +
                "<form name=\"mainform\" action=\"" + this.getTdsData().getAcsUrl() + "\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"PaReq\" value=\""+ this.tdsData.getPaReq() + "\">" +
                "<input type=\"hidden\" name=\"TermUrl\" value=\""+callBack+"/5"+"\">" +
                "<input type=\"hidden\" name=\"MD\" value=\""+ this.tdsData.getMd() + "\">" +
                "</form>" +
                "<SCRIPT LANGUAGE=\"Javascript\">" +
                "function OnLoadEvent() {" +
                "document.mainform.submit();" +
                "}" +
                "</SCRIPT>" +
                "</body>" +
                "</html>";
    }

    public String getMD(){
        return  this.tdsData.getMd();
    }

}