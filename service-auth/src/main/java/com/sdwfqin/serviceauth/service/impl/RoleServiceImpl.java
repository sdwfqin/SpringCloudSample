package com.sdwfqin.serviceauth.service.impl;

import com.sdwfqin.serviceauth.dao.RoleDao;
import com.sdwfqin.serviceauth.domain.RoleDo;
import com.sdwfqin.serviceauth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleDo get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public List<RoleDo> list(Map<String, Object> map) {
        return roleDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return roleDao.count(map);
    }

    @Override
    public int save(RoleDo roleDo) {
        return roleDao.save(roleDo);
    }

    @Override
    public int update(RoleDo roleDo) {
        return roleDao.update(roleDo);
    }

    @Override
    public int remove(Long id) {
        return roleDao.remove(id);
    }
}
