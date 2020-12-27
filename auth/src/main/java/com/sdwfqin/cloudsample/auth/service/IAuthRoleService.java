package com.sdwfqin.cloudsample.auth.service;

import com.sdwfqin.cloudsample.auth.dto.RoleDto;
import com.sdwfqin.cloudsample.common.entity.AuthRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
public interface IAuthRoleService extends IService<AuthRoleDO> {
    /**
     * 检查角色是否存在
     */
    boolean checkRole(Long id);

    /**
     * 根据名称获取角色信息
     *
     * @param name
     * @return
     */
    AuthRoleDO getRoleByName(String name);

    /**
     * 添加角色
     *
     * @param authRoleDto
     */
    AuthRoleDO add(RoleDto authRoleDto);

    /**
     * 角色列表
     *
     * @param map ids
     * @return
     */
    List<AuthRoleDO> getRoleList(Map<String, Object> map);

    /**
     * 获取角色列表
     *
     * @return
     */
    List<AuthRoleDO> getRolesByUserId(Long uid);

    /**
     * 角色列表
     *
     * @param map
     * @return
     */
    List<AuthRoleDO> getRoleContainPermissionList(Map<String, Object> map);

    /**
     * 角色列表
     *
     * @return
     */
    AuthRoleDO edit(RoleDto authRoleDto);

    /**
     * 删除角色
     *
     * @return
     */
    void delete(Long id);
}
