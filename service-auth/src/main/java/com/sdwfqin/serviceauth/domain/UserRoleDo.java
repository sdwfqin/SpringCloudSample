package com.sdwfqin.serviceauth.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleDo implements Serializable {

    private Long user_id;
    private Long role_id;
}
