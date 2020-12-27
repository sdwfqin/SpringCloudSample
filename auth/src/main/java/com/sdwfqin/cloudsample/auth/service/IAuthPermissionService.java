package com.sdwfqin.cloudsample.auth.service;

import com.sdwfqin.cloudsample.common.entity.AuthPermissionDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
public interface IAuthPermissionService extends IService<AuthPermissionDO> {
    /**
     * 获取指定用户的权限
     *
     * @return
     */
    List<AuthPermissionDO> getUserPermission(Long userId);

    /**
     * 获取指定用户的权限,最返回name字段
     *
     * @return
     */
    List<String> getUserPermissionList(Long userId);

    List<AuthPermissionDO> getRolePermission(Long roleId);

    List<AuthPermissionDO> getTreeList(List<AuthPermissionDO> rootList);

    /**
     * 删除权限
     *
     * @return
     */
    void delete(Long id);
}
