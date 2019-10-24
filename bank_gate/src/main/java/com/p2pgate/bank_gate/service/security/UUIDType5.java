package com.p2pgate.bank_gate.service.security;

import java.util.UUID;

/**
 *
 * Created by oakutsenko on 16.04.2018.
 */
public interface UUIDType5 {

    UUID getUUID(String name);
    UUID getUUID(String namespace, String name);
}
