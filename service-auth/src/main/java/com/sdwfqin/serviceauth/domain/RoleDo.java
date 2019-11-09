package com.sdwfqin.serviceauth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
public class RoleDo implements GrantedAuthority, Serializable {

    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
