package com.sdwfqin.serviceauth.service.impl;

import com.sdwfqin.serviceauth.dao.UserRoleDao;
import com.sdwfqin.serviceauth.domain.RoleDo;
import com.sdwfqin.serviceauth.domain.UserRoleDo;
import com.sdwfqin.serviceauth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<RoleDo> listRoleByUserId(Long userId) {
        return userRoleDao.listRoleByUserId(userId);
    }

    @Override
    public int save(UserRoleDo userRoleDo) {
        return userRoleDao.save(userRoleDo);
    }
}
