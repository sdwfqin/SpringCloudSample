package com.sdwfqin.cloudsample.auth.controller;

import com.sdwfqin.cloudsample.auth.dto.RoleDto;
import com.sdwfqin.cloudsample.auth.service.IAuthRoleService;
import com.sdwfqin.cloudsample.common.entity.AuthRoleDO;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import com.sdwfqin.cloudsample.common.result.Result;
import com.sdwfqin.cloudsample.common.result.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色相关
 * <p>
 *
 * @author 张钦
 * @date 2020/6/24
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping("/role")
@PreAuthorize("hasAuthority('system:role')")
public class AuthRoleController {

    @Autowired
    private IAuthRoleService authRoleService;

    @ApiOperation("添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", required = true),
            @ApiImplicitParam(name = "description", value = "角色描述", required = false),
            @ApiImplicitParam(name = "remark", value = "备注", required = false),
            @ApiImplicitParam(name = "permissions", value = "权限清单", required = true),
    })
    @PostMapping("add")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:role:add')")
    public Result<AuthRoleDO> add(@RequestBody RoleDto authRoleDto) {
        if (StringUtils.isEmpty(authRoleDto.getName())) {
            throw new ServiceException("角色名称不能为空");
        }
        if (authRoleDto.getPermissions() == null || authRoleDto.getPermissions().length == 0) {
            throw new ServiceException("权限清单不能为空");
        }

        AuthRoleDO roleByName = authRoleService.getRoleByName(authRoleDto.getName());
        if (roleByName != null) {
            throw new ServiceException("角色不能重复");
        }
        return ResultUtils.success(authRoleService.add(authRoleDto));
    }

    @ApiOperation("角色列表")
    @GetMapping("list")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:role:list')")
    public Result<List<AuthRoleDO>> list() {
        Map<String, Object> map = new LinkedHashMap<>();
        return ResultUtils.success(authRoleService.getRoleContainPermissionList(map));
    }

    @ApiOperation("角色编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true),
            @ApiImplicitParam(name = "name", value = "角色名称", required = false),
            @ApiImplicitParam(name = "description", value = "角色描述", required = false),
            @ApiImplicitParam(name = "remark", value = "备注", required = false),
            @ApiImplicitParam(name = "permissions", value = "权限清单", required = false),
    })
    @PostMapping("edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:role:edit')")
    public Result<AuthRoleDO> edit(@RequestBody RoleDto authRoleDto) {
        if (authRoleDto.getId() == null || authRoleDto.getId() <= 0) {
            throw new ServiceException("角色ID不正确");
        }
        return ResultUtils.success(authRoleService.edit(authRoleDto));
    }

    @ApiOperation("删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true),
    })
    @PostMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:role:delete')")
    public Result delete(Long id) {
        authRoleService.delete(id);
        return ResultUtils.success();
    }
}
