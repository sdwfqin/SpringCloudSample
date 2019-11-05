package com.sdwfqin.serviceauth.service;

import com.sdwfqin.serviceauth.dao.UserDao;
import com.sdwfqin.serviceauth.dao.UserRoleDao;
import com.sdwfqin.serviceauth.domain.RoleDo;
import com.sdwfqin.serviceauth.domain.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("userDetailService")
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao mUserDao;
    @Autowired
    private UserRoleDao mUserRoleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<UserDo> userDos = mUserDao.listUserByUserName(userName);
        if (userDos == null || userDos.size() == 0) {
            throw new UsernameNotFoundException("用不存在");
        }
        UserDo userDo = userDos.get(0);
        List<RoleDo> roleDos = mUserRoleDao.listRoleByUserId(userDo.getId());
        userDo.setAuthorities(roleDos);
        log.info(userDo.toString());
        return userDo;
    }
}
