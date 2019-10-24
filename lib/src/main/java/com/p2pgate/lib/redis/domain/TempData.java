package com.p2pgate.lib.redis.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Created by OAKutsenko on 21.04.2017.
 */
public class TempData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String operId;
    private String payId;
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    public TempData(final String operId){
        this.operId = operId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

    public  void setAdditionalProperty(Map<String, String> property) {

        this.additionalProperties = property;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
