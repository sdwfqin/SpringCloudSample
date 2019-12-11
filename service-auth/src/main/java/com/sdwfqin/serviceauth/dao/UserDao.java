package com.sdwfqin.serviceauth.dao;

import com.sdwfqin.serviceauth.domain.UserDo;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 根据用户名查找账号
     *
     * @param username
     * @return
     */
    List<UserDo> listUserByUserName(String username);

    /**
     * 添加账号
     *
     * @return
     */
    int save(UserDo userDo);
}
