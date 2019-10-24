package com.p2pgate.bank_gate.service.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Date;

/**
 *
 * Created by oakutsenko on 13.04.2018.
 */

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${pay.jwt.key}")
    private String key;

    @Value("${pay.jwt.iss}")
    private String iss;

    @Value("${pay.jwt.uuid}")
    private String nameSpace;

    @Autowired(required = false)
    private UUIDType5Impl uuid;

    @Override
    public String getJwtToken(String payid,  String sum, String transaction_id) {

       final Date now = new Date();

        return Jwts.builder()
                .setId(uuid.getUUID(nameSpace,payid+now).toString())
                .setIssuedAt(now)
                .setSubject("fixed")
                .setIssuer(iss)
                //.compressWith(CompressionCodecs.DEFLATE)
                .claim("pay_id", payid)
                .claim("sum", sum)
                .claim("transaction_id", transaction_id)
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode(key))
                .compact();
    }
}
