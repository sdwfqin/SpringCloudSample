package com.sdwfqin.cloudsample.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdwfqin.cloudsample.auth.dto.RoleDto;
import com.sdwfqin.cloudsample.auth.mapper.AuthRoleMapper;
import com.sdwfqin.cloudsample.auth.service.IAuthPermissionService;
import com.sdwfqin.cloudsample.auth.service.IAuthRolePermissionService;
import com.sdwfqin.cloudsample.auth.service.IAuthRoleService;
import com.sdwfqin.cloudsample.auth.service.IAuthUserRoleService;
import com.sdwfqin.cloudsample.common.entity.AuthPermissionDO;
import com.sdwfqin.cloudsample.common.entity.AuthRoleDO;
import com.sdwfqin.cloudsample.common.entity.AuthRolePermissionDO;
import com.sdwfqin.cloudsample.common.entity.AuthUserRoleDO;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import com.sdwfqin.cloudsample.common.utils.ContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRoleDO> implements IAuthRoleService {

    @Autowired
    IAuthRolePermissionService authRolePermissionService;
    @Autowired
    IAuthUserRoleService authUserRoleService;
    @Autowired
    IAuthPermissionService permissionService;

    @Override
    public boolean checkRole(Long id) {
        return baseMapper.selectById(id) != null;
    }

    @Override
    public AuthRoleDO getRoleByName(String name) {
        return baseMapper.getRoleByName(name);
    }

    @Override
    @Transactional
    public AuthRoleDO add(RoleDto authRoleDto) {
        checkPermission(authRoleDto.getPermissions());
        AuthRoleDO authRole = new AuthRoleDO();
        authRole.setName(authRoleDto.getName());
        authRole.setDescription(authRoleDto.getDescription());
        authRole.setRemark(authRoleDto.getRemark());
        authRole.setUserIdCreate(ContextUtils.getUserID());
        baseMapper.insert(authRole);
        for (Long permission : authRoleDto.getPermissions()) {
            AuthRolePermissionDO authRolePermission = new AuthRolePermissionDO();
            authRolePermission.setRoleId(authRole.getId());
            authRolePermission.setPremissionId(permission);
            authRolePermissionService.save(authRolePermission);
        }
        authRole.setPermission(permissionService.getRolePermission(authRole.getId()));
        return authRole;
    }

    @Override
    public List<AuthRoleDO> getRoleList(Map<String, Object> map) {
        return baseMapper.getRoleList(map);
    }

    @Override
    public List<AuthRoleDO> getRolesByUserId(Long uid) {
        LambdaQueryWrapper<AuthUserRoleDO> queryWrapper = new QueryWrapper<AuthUserRoleDO>().lambda()
                .eq(uid != null, AuthUserRoleDO::getUserId, uid);
        List<AuthUserRoleDO> authUserRoles = authUserRoleService.list(queryWrapper);
        List<Long> queryRole = new ArrayList<>();
        authUserRoles.forEach(authUserRole -> {
            queryRole.add(authUserRole.getRoleId());
        });
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ids", queryRole);
        return getRoleList(map);
    }

    @Override
    public List<AuthRoleDO> getRoleContainPermissionList(Map<String, Object> map) {
        List<AuthRoleDO> roleList = getRoleList(map);
        roleList.forEach(authRole -> {
            List<AuthPermissionDO> rolePermission = permissionService.getRolePermission(authRole.getId());
            List<Long> permissionIdList = new ArrayList<>();
            rolePermission.forEach(authPermission -> {
                permissionIdList.add(authPermission.getId());
            });
            authRole.setPermission(permissionService.getTreeList(rolePermission));
            Long[] permissionIdArray = new Long[permissionIdList.size()];
            permissionIdList.toArray(permissionIdArray);
            authRole.setPermissionIdArray(permissionIdArray);
        });
        return roleList;
    }

    @Override
    @Transactional
    public AuthRoleDO edit(RoleDto authRoleDto) {
        AuthRoleDO authRole = baseMapper.getRoleById(authRoleDto.getId());
        if (authRole == null) {
            throw new ServiceException("没有查询到这个角色");
        }
        if (authRoleDto.getPermissions() != null && authRoleDto.getPermissions().length > 0) {
            checkPermission(authRoleDto.getPermissions());

            LambdaQueryWrapper<AuthRolePermissionDO> queryWrapper = new QueryWrapper<AuthRolePermissionDO>().lambda()
                    .eq(true, AuthRolePermissionDO::getRoleId, authRoleDto.getId());
            authRolePermissionService.remove(queryWrapper);

            for (Long permission : authRoleDto.getPermissions()) {
                AuthRolePermissionDO authRolePermission = new AuthRolePermissionDO();
                authRolePermission.setRoleId(authRole.getId());
                authRolePermission.setPremissionId(permission);
                authRolePermissionService.save(authRolePermission);
            }
        }
        if (!StringUtils.isEmpty(authRoleDto.getName())) {
            authRole.setName(authRoleDto.getName());
        }
        if (!StringUtils.isEmpty(authRoleDto.getDescription())) {
            authRole.setDescription(authRoleDto.getDescription());
        }
        if (!StringUtils.isEmpty(authRoleDto.getRemark())) {
            authRole.setRemark(authRoleDto.getRemark());
        }
        baseMapper.updateById(authRole);
        authRole.setPermission(permissionService.getRolePermission(authRole.getId()));
        return authRole;
    }

    @Override
    public void delete(Long id) {
        LambdaQueryWrapper<AuthUserRoleDO> userRoleLambdaQueryWrapper = new QueryWrapper<AuthUserRoleDO>().lambda();
        userRoleLambdaQueryWrapper.eq(true, AuthUserRoleDO::getRoleId, id);
        List<AuthUserRoleDO> authUserRoles = authUserRoleService.list(userRoleLambdaQueryWrapper);
        if (authUserRoles != null && authUserRoles.size() > 0) {
            throw new ServiceException("当前已有用户使用此角色，请先取消关联关系");
        }
        baseMapper.deleteById(id);
    }

    /**
     * 检查是否存在这个权限
     *
     * @param permissions
     */
    private void checkPermission(Long[] permissions) {
        for (Long permission : permissions) {
            AuthPermissionDO authPermission = permissionService.getById(permission);
            if (authPermission == null) {
                throw new ServiceException("无此权限：id=" + permission);
            }
        }
    }
}
