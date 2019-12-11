package com.sdwfqin.serviceauth.service;

import com.sdwfqin.serviceauth.domain.RoleDo;
import com.sdwfqin.serviceauth.domain.UserRoleDo;

import java.util.List;

/**
 * 用户Service
 * <p>
 *
 * @author 张钦
 * @date 2019/12/11
 */
public interface UserRoleService {

    List<RoleDo> listRoleByUserId(Long userId);

    int save(UserRoleDo userRoleDo);
}
