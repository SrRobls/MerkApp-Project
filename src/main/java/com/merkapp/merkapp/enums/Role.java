package com.merkapp.merkapp.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    //Manejamos los roles de los usuarios, esto mediante ENUMS de tipo autoridad de seguridad
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
