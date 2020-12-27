package com.sdwfqin.cloudsample.auth.controller;

import com.sdwfqin.cloudsample.auth.service.IAuthPermissionService;
import com.sdwfqin.cloudsample.common.entity.AuthPermissionDO;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import com.sdwfqin.cloudsample.common.result.Result;
import com.sdwfqin.cloudsample.common.result.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 权限相关
 * <p>
 *
 * @author 张钦
 * @date 2020/6/24
 */
@Api(tags = "权限管理")
@Controller
@RequestMapping("/permission")
@PreAuthorize("hasAuthority('system:permission')")
public class AuthPermissionController {

    @Autowired
    private IAuthPermissionService permissionService;

    @ApiOperation("权限列表")
    @GetMapping("list")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:permission:list')")
    public Result<List<AuthPermissionDO>> list() {
        return ResultUtils.success(permissionService.list());
    }


    @ApiOperation("新增权限")
    @ResponseBody
    @PreAuthorize("hasAuthority('permission:add')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<AuthPermissionDO> add(@RequestBody AuthPermissionDO authPermission) {
        authPermission.setIsDeleted(false);
        boolean addSuccess = permissionService.save(authPermission);
        if (addSuccess) {
            return ResultUtils.success(authPermission);
        } else {
            throw new ServiceException("添加失败");
        }
    }

    @ApiOperation("删除权限byId")
    @GetMapping("remove")
    @ResponseBody
    @PreAuthorize("hasAuthority('permission:delete')")
    public Result remove(@ApiParam(value = "权限id", required = true)
                         @RequestParam Long permissionId) {

        permissionService.delete(permissionId);

        return ResultUtils.success();
    }

    @ApiOperation("更新权限信息")
    @GetMapping("update")
    @ResponseBody
    @PreAuthorize("hasAuthority('permission:update')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody AuthPermissionDO auth) {
        AuthPermissionDO authPermission = permissionService.getById(auth.getId());

        if (authPermission == null) {
            throw new ServiceException("该id权限信息数据不存在，更新失败！");
        }
        auth.setGmtModified(new Date());
        if (permissionService.updateById(auth)) {
            return ResultUtils.success();

        } else {
            throw new ServiceException("更新失败");
        }
    }
}
