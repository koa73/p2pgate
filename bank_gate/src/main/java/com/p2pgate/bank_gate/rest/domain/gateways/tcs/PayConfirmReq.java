package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * Created by OAKutsenko on 20.04.2017.
 */
public class PayConfirmReq {


    @JsonProperty("3DSecure")
    private String paRes;

    public PayConfirmReq(String paRes){
        this.paRes = paRes;
    }

    @JsonProperty("3DSecure")
    public String getPaRes() {
        return paRes;
    }

    @JsonProperty("3DSecure")
    public void setPaRes(String paRes) {
        this.paRes = paRes;
    }

    @JsonIgnore
    @Override
    public String toString() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e){

            return null;
        }
    }
}
