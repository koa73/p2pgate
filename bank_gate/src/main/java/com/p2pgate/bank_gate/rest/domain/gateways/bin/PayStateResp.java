package com.p2pgate.bank_gate.rest.domain.gateways.bin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "request_id",
        "fee_amount",
        "state",
        "state_id",
        "src_number",
        "tgt_number",
        "created_date",
        "processed_date",
        "amount",
        "src_payment_system",
        "tgt_payment_system",
        "status_description",
        "rrn"
})
public class PayStateResp {

    @JsonProperty("request_id")
    private Integer requestId;
    @JsonProperty("fee_amount")
    private Double feeAmount;
    @JsonProperty("state")
    private Object state;
    @JsonProperty("state_id")
    private String stateId;
    @JsonProperty("src_number")
    private String srcNumber;
    @JsonProperty("tgt_number")
    private String tgtNumber;
    @JsonProperty("created_date")
    private String createdDate;
    @JsonProperty("processed_date")
    private String processedDate;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("src_payment_system")
    private String srcPaymentSystem;
    @JsonProperty("tgt_payment_system")
    private String tgtPaymentSystem;
    @JsonProperty("status_description")
    private String statusDescription;
    @JsonProperty("rrn")
    private String rrn;

    @JsonProperty("request_id")
    public Integer getRequestId() {
        return requestId;
    }

    @JsonProperty("request_id")
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("fee_amount")
    public Double getFeeAmount() {
        return feeAmount;
    }

    @JsonProperty("fee_amount")
    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    @JsonProperty("state")
    public Object getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(Object state) {
        this.state = state;
    }

    @JsonProperty("state_id")
    public String getStateId() {
        return stateId;
    }

    @JsonProperty("state_id")
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    @JsonProperty("src_number")
    public String getSrcNumber() {
        return srcNumber;
    }

    @JsonProperty("src_number")
    public void setSrcNumber(String srcNumber) {
        this.srcNumber = srcNumber;
    }

    @JsonProperty("tgt_number")
    public String getTgtNumber() {
        return tgtNumber;
    }

    @JsonProperty("tgt_number")
    public void setTgtNumber(String tgtNumber) {
        this.tgtNumber = tgtNumber;
    }

    @JsonProperty("created_date")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("created_date")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("processed_date")
    public String getProcessedDate() {
        return processedDate;
    }

    @JsonProperty("processed_date")
    public void setProcessedDate(String processedDate) {
        this.processedDate = processedDate;
    }

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("src_payment_system")
    public String getSrcPaymentSystem() {
        return srcPaymentSystem;
    }

    @JsonProperty("src_payment_system")
    public void setSrcPaymentSystem(String srcPaymentSystem) {
        this.srcPaymentSystem = srcPaymentSystem;
    }

    @JsonProperty("tgt_payment_system")
    public String getTgtPaymentSystem() {
        return tgtPaymentSystem;
    }

    @JsonProperty("tgt_payment_system")
    public void setTgtPaymentSystem(String tgtPaymentSystem) {
        this.tgtPaymentSystem = tgtPaymentSystem;
    }

    @JsonProperty("status_description")
    public String getStatusDescription() {
        return statusDescription;
    }

    @JsonProperty("status_description")
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @JsonProperty("rrn")
    public String getRrn() {
        return rrn;
    }

    @JsonProperty("rrn")
    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

}