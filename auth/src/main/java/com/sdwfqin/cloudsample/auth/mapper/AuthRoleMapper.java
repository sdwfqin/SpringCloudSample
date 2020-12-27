package com.sdwfqin.cloudsample.auth.mapper;

import com.sdwfqin.cloudsample.common.entity.AuthRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
public interface AuthRoleMapper extends BaseMapper<AuthRoleDO> {
    AuthRoleDO getRoleById(Long id);

    AuthRoleDO getRoleByName(String name);

    List<AuthRoleDO> getRoleList(Map<String, Object> map);
}
