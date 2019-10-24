package com.p2pgate.vk_gate.rest.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 *
 * Created by OAKutsenko on 06.09.2019.
 */

public class PayApiException extends Exception {

    @JsonIgnoreProperties(value =
            {"cause", "stackTrace", "localizedMessage", "suppressed", "message", "status"},
            ignoreUnknown=true)



    private int errCode;

    private String reason;

    private String payId;


    public PayApiException(){}

    public PayApiException(String payId, int errCode){
        this.payId = payId;
        this.errCode = errCode;
        this.reason = null;
    }


    public PayApiException(String payId, int errCode, String causeReason){
        this.payId = payId;
        this.errCode = errCode;
        this.reason = causeReason;
    }

    public String getCauseReason() {
        return reason;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getReason() {
        return reason;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public void setReason(String causeReason) {
        this.reason = causeReason;
    }

}
