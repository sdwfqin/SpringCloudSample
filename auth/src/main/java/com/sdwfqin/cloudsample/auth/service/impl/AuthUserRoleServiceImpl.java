package com.sdwfqin.cloudsample.auth.service.impl;

import com.sdwfqin.cloudsample.common.entity.AuthUserRoleDO;
import com.sdwfqin.cloudsample.auth.mapper.AuthUserRoleMapper;
import com.sdwfqin.cloudsample.auth.service.IAuthUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
@Service
public class AuthUserRoleServiceImpl extends ServiceImpl<AuthUserRoleMapper, AuthUserRoleDO> implements IAuthUserRoleService {

}
