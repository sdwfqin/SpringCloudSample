package com.sdwfqin.serviceauth.controller;

import com.sdwfqin.common.result.Result;
import com.sdwfqin.common.result.ResultUtils;
import com.sdwfqin.common.utils.PageUtils;
import com.sdwfqin.common.utils.QueryMap;
import com.sdwfqin.serviceauth.config.Constant;
import com.sdwfqin.serviceauth.domain.RoleDo;
import com.sdwfqin.serviceauth.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/role")
@PreAuthorize(value = "hasRole('" + Constant.ROLE_ADMIN + "')")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result<Object> exit(Map<String, Object> map) {

        QueryMap queryMap = new QueryMap(map);
        List<RoleDo> roleDoList = roleService.list(queryMap);

        int count = roleService.count(queryMap);

        PageUtils pageUtils = new PageUtils(roleDoList, count);

        return ResultUtils.success(pageUtils);
    }


}