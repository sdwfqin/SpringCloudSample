package com.sdwfqin.serviceauth.service;

import com.sdwfqin.serviceauth.domain.UserDo;

import java.util.List;

/**
 * 用户Service
 * <p>
 *
 * @author 张钦
 * @date 2019/12/11
 */
public interface UserService {

    List<UserDo> listUserByUserName(String username);

    int save(UserDo userDo, Long[] roleIds);
}
