package com.sdwfqin.cloudsample.auth.mapper;

import com.sdwfqin.cloudsample.common.entity.AuthPermissionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
public interface AuthPermissionMapper extends BaseMapper<AuthPermissionDO> {
    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    List<String> getUserPermissionList(Long userId);
    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    List<AuthPermissionDO> getUserPermission(Long userId);
    /**
     * 获取角色权限列表
     *
     * @param roleId
     * @return
     */
    List<AuthPermissionDO> getRolePermission(Long roleId);

}
