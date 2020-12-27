package com.sdwfqin.cloudsample.auth.service.impl;

import com.sdwfqin.cloudsample.auth.service.IAuthPermissionService;
import com.sdwfqin.cloudsample.auth.service.IAuthUserService;
import com.sdwfqin.cloudsample.common.entity.AuthUserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IAuthUserService authUserService;
    @Autowired
    IAuthPermissionService permissionService;

    @PostConstruct
    public void initData() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserDO user = authUserService.getUserDataByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setPermissions(permissionService.getUserPermissionList(user.getId()));
        return user;
    }
}
