package com.isa.PrivateClinicContracts.security.authorization;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),
    USER_CREATE("user:create")
    ;

    private final String permission;
}
