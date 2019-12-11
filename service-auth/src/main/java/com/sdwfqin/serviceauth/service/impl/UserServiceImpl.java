package com.sdwfqin.serviceauth.service.impl;

import com.sdwfqin.serviceauth.dao.UserDao;
import com.sdwfqin.serviceauth.domain.UserDo;
import com.sdwfqin.serviceauth.domain.UserRoleDo;
import com.sdwfqin.serviceauth.service.UserRoleService;
import com.sdwfqin.serviceauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<UserDo> listUserByUserName(String username) {
        return userDao.listUserByUserName(username);
    }

    @Override
    public int save(UserDo userDo, Long[] roleIds) {
        int size = userDao.save(userDo);

        log.info(roleIds.toString());
        log.info(userDo.getId() + "");

        UserRoleDo userRoleDo = new UserRoleDo();
        userRoleDo.setUserId(userDo.getId());
        for (int i = 0; i < roleIds.length; i++) {
            userRoleDo.setRoleId(roleIds[i]);
            userRoleService.save(userRoleDo);
        }

        return size;
    }
}
