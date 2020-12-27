package com.sdwfqin.cloudsample.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdwfqin.cloudsample.auth.dto.UserDto;
import com.sdwfqin.cloudsample.auth.dto.UserEditDto;
import com.sdwfqin.cloudsample.auth.mapper.AuthUserMapper;
import com.sdwfqin.cloudsample.auth.service.IAuthRoleService;
import com.sdwfqin.cloudsample.auth.service.IAuthUserRoleService;
import com.sdwfqin.cloudsample.auth.service.IAuthUserService;
import com.sdwfqin.cloudsample.common.entity.AuthUserDO;
import com.sdwfqin.cloudsample.common.entity.AuthUserRoleDO;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import com.sdwfqin.cloudsample.common.utils.ContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUserDO> implements IAuthUserService {

    @Autowired
    IAuthUserRoleService authUserRoleService;
    @Autowired
    IAuthRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthUserDO getUserDataByName(String name) {
        LambdaQueryWrapper<AuthUserDO> queryWrapper = new QueryWrapper<AuthUserDO>().lambda()
                .eq(name != null, AuthUserDO::getUsername, name);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthUserDO addUser(UserDto userDto) {
        AuthUserDO authUser = new AuthUserDO();
        authUser.setUsername(userDto.getUserName());
        authUser.setPassword(passwordEncoder.encode(userDto.getPassWord()));
        authUser.setNickName(userDto.getNickName());
        authUser.setPhoneNumber(userDto.getPhoneNumber());
        authUser.setAvatar(userDto.getAvatar());
        baseMapper.insert(authUser);
        for (Long roleId : userDto.getRoleIds()) {
            AuthUserRoleDO authUserRole = new AuthUserRoleDO();
            authUserRole.setUserId(authUser.getId());
            authUserRole.setRoleId(roleId);
            authUserRoleService.save(authUserRole);
        }
        return authUser;
    }

    @Override
    public IPage<AuthUserDO> list(Integer pageNum, Integer pageSize, Long roleId) {
        Page<AuthUserDO> page = new Page<>(pageNum, pageSize);
        return baseMapper.getUserList(page, roleId);
    }

    @Override
    public AuthUserDO editCurrentUser(UserEditDto authUserEditDto) {
        AuthUserDO authUser = baseMapper.selectById(ContextUtils.getUserID());
        if (!StringUtils.isEmpty(authUserEditDto.getNickName())) {
            authUser.setNickName(authUserEditDto.getNickName());
        }
        if (!StringUtils.isEmpty(authUserEditDto.getAvatar())) {
            authUser.setAvatar(authUserEditDto.getAvatar());
        }
        if (null != authUserEditDto.getBirthday()) {
            authUser.setBirthday(authUserEditDto.getBirthday());
        }
        if (null != authUserEditDto.getSex()) {
            authUser.setSex(authUserEditDto.getSex());
        }
        baseMapper.updateById(authUser);

        return authUser;
    }

    @Override
    public void editCurrentUserPassword(Long uesrId, String oldPassword, String newPassword) {
        AuthUserDO authUser = baseMapper.selectById(uesrId);
        if (passwordEncoder.matches(authUser.getPassword(), oldPassword)) {
            authUser.setPassword(passwordEncoder.encode(newPassword));
            baseMapper.updateById(authUser);
        } else {
            throw new ServiceException("原密码错误!");
        }
    }

    @Override
    @Transactional
    public AuthUserDO editUser(Long id, String userName, String nikeName, String avatar, String phoneNumber, String passWord, Long[] roleIds) {
        AuthUserDO authUser = baseMapper.selectById(id);
        if (authUser == null) {
            throw new ServiceException("没有查询到当前用户");
        }
        if (!StringUtils.isEmpty(userName)) {
            authUser.setUsername(userName);
        }
        if (!StringUtils.isEmpty(nikeName)) {
            authUser.setNickName(nikeName);
        }
        if (!StringUtils.isEmpty(avatar)) {
            authUser.setAvatar(avatar);
        }
        if (!StringUtils.isEmpty(phoneNumber)) {
            authUser.setPhoneNumber(phoneNumber);
        }
        if (!StringUtils.isEmpty(passWord)) {
            authUser.setPassword(passwordEncoder.encode(passWord));
        }
        authUser.setGmtModified(new Date());
        if (roleIds != null && roleIds.length > 0) {
            for (Long roleId : roleIds) {
                if (!roleService.checkRole(roleId)) {
                    throw new ServiceException("角色不存在");
                }
            }
            // 删除角色关联
            LambdaQueryWrapper<AuthUserRoleDO> queryWrapper = new QueryWrapper<AuthUserRoleDO>().lambda()
                    .eq(true, AuthUserRoleDO::getUserId, id);
            authUserRoleService.remove(queryWrapper);
            // 添加角色
            for (Long roleId : roleIds) {
                AuthUserRoleDO authUserRole = new AuthUserRoleDO();
                authUserRole.setUserId(authUser.getId());
                authUserRole.setRoleId(roleId);
                authUserRoleService.save(authUserRole);
            }
        }
        baseMapper.updateById(authUser);
        return authUser;
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
