package com.sdwfqin.serviceauth.dao;

import com.sdwfqin.serviceauth.domain.UserDo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    @Select("SELECT * FROM user WHERE `username` = #{username}")
    List<UserDo> listUserByUserName(String username);
}
