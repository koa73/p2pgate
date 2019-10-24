package com.p2pgate.bank_gate.rest.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;


/**
 *
 * Created by OAKutsenko on 06.09.2019.
 */

public class PayApiException extends Exception {

    @Value("${page.error}")
    private String errorURL;

    @JsonIgnoreProperties(value =
            {"cause", "stackTrace", "localizedMessage", "suppressed", "message", "status"},
            ignoreUnknown=true)


    private String errMsg;

    private String reason;

    private String payId;


    public PayApiException(){}

    public PayApiException(String payId, String errMsg){
        this.payId = payId;
        this.errMsg = errMsg;
        this.reason = null;
    }


    public PayApiException(String payId, String errMsg, String causeReason){
        this.payId = payId;
        this.errMsg = errMsg;
        this.reason = causeReason;
    }

    public String getCauseReason() {
        return reason;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
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

    @Override
    public String toString() {

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta content=\"HTML Tidy for Java (vers. 26 сен 2004), see www.w3.org\" name=\"generator\"/>\n" +
                "    <title>Phone4Pay \u0097 Error-PG</title>\n" +
                "    <meta content=\"0; url="+errorURL+"?payid="+payId+"&error="+errMsg+"\" http-equiv=\"refresh\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<script type=\"text/javascript\">window.location.href = \""+errorURL+"?payid="+payId+"&error="+errMsg+"\";</script>\n" +
                "</body>\n" +
                "</html>";
    }
}
