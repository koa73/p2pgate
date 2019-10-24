package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

/**
 * Created by OAKutsenko on 30.08.2019.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "trackingId",
        "operationTicket",
        "initialOperation",
        "resultCode",
        "confirmations",
        "confirmationData"
})
public class PayResp {

    @JsonProperty("trackingId")
    private String trackingId;
    @JsonProperty("operationTicket")
    private String operationTicket;
    @JsonProperty("initialOperation")
    private String initialOperation;
    @JsonProperty("resultCode")
    private String resultCode;
    @JsonProperty("confirmations")
    private List<String> confirmations = null;
    @JsonProperty("confirmationData")
    private ConfirmationData confirmationData;
    @JsonProperty("plainMessage")
    private String plainMessage;

    @JsonProperty("trackingId")
    public String getTrackingId() {
        return trackingId;
    }

    @JsonProperty("trackingId")
    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    @JsonProperty("operationTicket")
    public String getOperationTicket() {
        return operationTicket;
    }

    @JsonProperty("operationTicket")
    public void setOperationTicket(String operationTicket) {
        this.operationTicket = operationTicket;
    }

    @JsonProperty("initialOperation")
    public String getInitialOperation() {
        return initialOperation;
    }

    @JsonProperty("initialOperation")
    public void setInitialOperation(String initialOperation) {
        this.initialOperation = initialOperation;
    }

    @JsonProperty("resultCode")
    public String getResultCode() {
        return resultCode;
    }

    @JsonProperty("resultCode")
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @JsonProperty("confirmations")
    public List<String> getConfirmations() {
        return confirmations;
    }

    @JsonProperty("confirmations")
    public void setConfirmations(List<String> confirmations) {
        this.confirmations = confirmations;
    }

    @JsonProperty("confirmationData")
    public ConfirmationData getConfirmationData() {
        return confirmationData;
    }

    @JsonProperty("confirmationData")
    public void setConfirmationData(ConfirmationData confirmationData) {
        this.confirmationData = confirmationData;
    }

    @JsonProperty("plainMessage")
    public String getPlainMessage() {
        return plainMessage;
    }

    @JsonProperty("plainMessage")
    public void setPlainMessage(String plainMessage) {
        this.plainMessage = plainMessage;
    }

    public String getPaymentId(){
        return getConfirmationData().get3DSecure().getPaymentId();
    }


    public String getMD(){
        return  this.confirmationData.get3DSecure().getMerchantData();
    }


    public String getACSUrl(){
        return this.confirmationData.get3DSecure().getUrl();
    }

    public String getPaReq(){
        return this.confirmationData.get3DSecure().getRequestSecretCode();
    }


    public String getPage(String callBack){

        return  "<html>" +
                "<body OnLoad=\"OnLoadEvent();\">" +
                "<form name=\"mainform\" action=\"" + getACSUrl() + "\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"PaReq\" value=\""+ getPaReq() + "\">" +
                "<input type=\"hidden\" name=\"TermUrl\" value=\""+ callBack +"/1"+"\">" +
                "<input type=\"hidden\" name=\"MD\" value=\""+ getMD() + "\">" +
                "</form>" +
                "<SCRIPT LANGUAGE=\"Javascript\">" +
                "function OnLoadEvent(){" +
                "document.mainform.submit();" +
                "}" +
                "</SCRIPT>" +
                "</body>" +
                "</html>";
    }
}