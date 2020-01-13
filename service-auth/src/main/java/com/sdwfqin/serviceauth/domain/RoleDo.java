package com.sdwfqin.serviceauth.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.sql.Date;

@Data
public class RoleDo implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = -1307735236443661927L;

    private Long id;
    private String name;
    private String description;
    private String remark;
    private Long userIdCreate;
    private Date createTime;
    private Date updateTime;
    private String userNameCreate;

    @Override
    public String getAuthority() {
        return name;
    }
}
