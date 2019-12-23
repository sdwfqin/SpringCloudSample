package com.sdwfqin.serviceauth.service;

import com.sdwfqin.serviceauth.domain.RoleDo;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * <p>
 *
 * @author 张钦
 * @date 2019/12/11
 */
public interface RoleService {

    RoleDo get(Long id);

    List<RoleDo> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RoleDo roleDo);

    int update(RoleDo roleDo);

    int remove(Long id);
}
