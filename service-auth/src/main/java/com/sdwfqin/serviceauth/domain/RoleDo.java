package com.sdwfqin.serviceauth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class RoleDo implements GrantedAuthority {

    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
