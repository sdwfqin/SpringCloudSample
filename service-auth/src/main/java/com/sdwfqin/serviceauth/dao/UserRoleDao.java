package com.sdwfqin.serviceauth.dao;

import com.sdwfqin.serviceauth.domain.RoleDo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleDao {

    @Select("SELECT role.id,role.name FROM user_role " +
            "LEFT JOIN role ON user_role.role_id = role.id " +
            "WHERE `user_id` = #{userId}")
    List<RoleDo> listRoleByUserId(Long userId);

}
