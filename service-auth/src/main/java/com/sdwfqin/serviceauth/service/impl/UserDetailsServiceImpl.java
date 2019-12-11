package com.sdwfqin.serviceauth.service.impl;

import com.sdwfqin.serviceauth.domain.RoleDo;
import com.sdwfqin.serviceauth.domain.UserDo;
import com.sdwfqin.serviceauth.service.UserRoleService;
import com.sdwfqin.serviceauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<UserDo> userDos = userService.listUserByUserName(userName);
        if (userDos == null || userDos.size() == 0) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDo userDo = userDos.get(0);
        List<RoleDo> roleDos = userRoleService.listRoleByUserId(userDo.getId());
        userDo.setAuthorities(roleDos);
        log.info(userDo.toString());
        return userDo;
    }
}
