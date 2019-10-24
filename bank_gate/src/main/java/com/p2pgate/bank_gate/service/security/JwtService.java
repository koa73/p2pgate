package com.p2pgate.bank_gate.service.security;

/**
 *
 * Created by oakutsenko on 13.04.2018.
 */

public interface JwtService {

    String getJwtToken(String payid,  String sum, String transaction_id);
}
