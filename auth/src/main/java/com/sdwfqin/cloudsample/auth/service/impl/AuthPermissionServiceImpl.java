package com.sdwfqin.cloudsample.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdwfqin.cloudsample.auth.mapper.AuthPermissionMapper;
import com.sdwfqin.cloudsample.auth.service.IAuthPermissionService;
import com.sdwfqin.cloudsample.auth.service.IAuthRolePermissionService;
import com.sdwfqin.cloudsample.common.entity.AuthPermissionDO;
import com.sdwfqin.cloudsample.common.entity.AuthRolePermissionDO;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
@Service
public class AuthPermissionServiceImpl extends ServiceImpl<AuthPermissionMapper, AuthPermissionDO> implements IAuthPermissionService {

    @Autowired
    IAuthRolePermissionService authRolePermissionService;

    @Override
    public List<AuthPermissionDO> list() {
        List<AuthPermissionDO> PermissionDos = baseMapper.selectList(new QueryWrapper<>());
        return getTreeList(PermissionDos);
    }

    @Override
    public List<AuthPermissionDO> getUserPermission(Long userId) {
        List<AuthPermissionDO> PermissionDos = baseMapper.getUserPermission(userId);
        return getTreeList(PermissionDos);
    }

    @Override
    public List<String> getUserPermissionList(Long userId) {
        return baseMapper.getUserPermissionList(userId);
    }

    @Override
    public List<AuthPermissionDO> getRolePermission(Long roleId) {
        return baseMapper.getRolePermission(roleId);
    }

    /**
     * 无限极列表
     *
     * @param rootList
     * @return
     */
    public List<AuthPermissionDO> getTreeList(List<AuthPermissionDO> rootList) {
        List<AuthPermissionDO> PermissionDosResult = new ArrayList<>();
        rootList.forEach(PermissionDo -> {
            if (PermissionDo.getParentId() == 0) {
                PermissionDosResult.add(PermissionDo);
            }
        });
        PermissionDosResult.forEach(PermissionDo -> {
            PermissionDo.setChildPermissions(getChildPermissions(PermissionDo.getId(), rootList));
        });

        return PermissionDosResult;
    }

    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootList 要查找的列表
     * @return
     */
    private List<AuthPermissionDO> getChildPermissions(Long id, List<AuthPermissionDO> rootList) {
        //子菜单
        List<AuthPermissionDO> childList = new ArrayList<>();
        rootList.forEach(PermissionDo -> {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (PermissionDo.getParentId().equals(id)) {
                childList.add(PermissionDo);
            }
        });
        // 把子菜单的子菜单再循环一遍
        childList.forEach(PermissionDo -> {
            PermissionDo.setChildPermissions(getChildPermissions(PermissionDo.getId(), rootList));
        });
        // 递归退出条件
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        return childList;
    }

    @Override
    public void delete(Long id) {
        List<AuthRolePermissionDO> authList = authRolePermissionService.list(new QueryWrapper<AuthRolePermissionDO>().eq("premission_id", id));
        if (authList != null && authList.size() > 0) {
            throw new ServiceException("当前已有角色使用此权限，请先取消关联关系");
        }
        LambdaQueryWrapper<AuthPermissionDO> queryWrapper = new QueryWrapper<AuthPermissionDO>().lambda()
                .eq(true, AuthPermissionDO::getParentId, id);
        List<AuthPermissionDO> PermissionDos = baseMapper.selectList(queryWrapper);
        if (PermissionDos.size() > 0) {
            throw new ServiceException("请先删除子权限");
        }
        baseMapper.deleteById(id);
    }

}
