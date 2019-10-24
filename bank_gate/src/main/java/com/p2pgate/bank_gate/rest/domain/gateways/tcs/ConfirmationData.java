package com.p2pgate.bank_gate.rest.domain.gateways.tcs;

/**
 * Created by OAKutsenko on 30.08.2019.
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "3DSecure"
})
public class ConfirmationData {

    @JsonProperty("3DSecure")
    private TDSecure _3DSecure;

    @JsonProperty("3DSecure")
    public TDSecure get3DSecure() {
        return _3DSecure;
    }

    @JsonProperty("3DSecure")
    public void set3DSecure(TDSecure _3DSecure) {
        this._3DSecure = _3DSecure;
    }

}
