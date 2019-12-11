package com.sdwfqin.serviceauth.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleDo implements Serializable {

    private Long userId;
    private Long roleId;
}
