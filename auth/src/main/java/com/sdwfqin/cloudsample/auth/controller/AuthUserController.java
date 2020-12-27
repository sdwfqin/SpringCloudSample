package com.sdwfqin.cloudsample.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdwfqin.cloudsample.auth.dto.UserDto;
import com.sdwfqin.cloudsample.auth.dto.UserEditDto;
import com.sdwfqin.cloudsample.auth.service.IAuthPermissionService;
import com.sdwfqin.cloudsample.auth.service.IAuthRoleService;
import com.sdwfqin.cloudsample.auth.service.IAuthUserService;
import com.sdwfqin.cloudsample.common.entity.AuthUserDO;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import com.sdwfqin.cloudsample.common.result.Result;
import com.sdwfqin.cloudsample.common.result.ResultUtils;
import com.sdwfqin.cloudsample.common.utils.ContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户相关
 * <p>
 *
 * @author 张钦
 * @date 2020/6/23
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class AuthUserController {

    @Autowired
    private IAuthUserService userService;
    @Autowired
    private IAuthRoleService authRoleService;
    @Autowired
    private IAuthPermissionService permissionService;

    @GetMapping("current")
    public Principal getUser(Principal principal) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(principal.toString());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    /**
     * 获取指定用户的用户信息
     * @param userId
     * @return
     */
    @GetMapping("getUserById")
    public Result<AuthUserDO> getUserById(Long userId) {
        return ResultUtils.success(userService.getById(userId));
    }

    @ApiOperation("添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true),
            @ApiImplicitParam(name = "passWord", value = "密码", defaultValue = "123456", required = true),
            @ApiImplicitParam(name = "nikeName", value = "姓名", required = true),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = true),
            @ApiImplicitParam(name = "roleIds", value = "角色Id", required = true, dataTypeClass = Long.class, allowMultiple = true)
    })
    @PostMapping("add")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:user:add')")
    public Result<AuthUserDO> add(@RequestBody UserDto userDto) {

        if (StringUtils.isEmpty(userDto.getUserName())) {
            throw new ServiceException("用户名不能为空");
        }
        if (StringUtils.isEmpty(userDto.getPassWord())) {
            throw new ServiceException("密码不能为空");
        }
        if (StringUtils.isEmpty(userDto.getNickName())) {
            throw new ServiceException("姓名不能为空");
        }
        if (StringUtils.isEmpty(userDto.getPhoneNumber())) {
            throw new ServiceException("手机号码不能为空");
        }
        if (userDto.getRoleIds().length == 0) {
            throw new ServiceException("角色不能为空");
        }

        AuthUserDO authUser = userService.getUserDataByName(userDto.getUserName());
        if (authUser != null) {
            throw new ServiceException("用户名不可重复");
        }
        for (Long roleId : userDto.getRoleIds()) {
            if (!authRoleService.checkRole(roleId)) {
                throw new ServiceException("角色不存在");
            }
        }

        AuthUserDO authUserResponse = userService.addUser(userDto);
        authUserResponse.getAuthRoles().addAll(authRoleService.getRolesByUserId(authUserResponse.getId()));

        return ResultUtils.success(authUserResponse);
    }

    @ApiOperation("用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false),
            @ApiImplicitParam(name = "pageNum", paramType = "query", value = "页数 1开始", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "每页条数", required = true, dataTypeClass = String.class)
    })
    @GetMapping("list")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:user:list')")
    public Result<IPage<AuthUserDO>> list(Integer pageNum, Integer pageSize, Long roleId) {
        IPage<AuthUserDO> list = userService.list(pageNum, pageSize, roleId);
        return ResultUtils.success(list);
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("getCurrentUserData")
    @ResponseBody
    public Result<Map<String, Object>> getUserData() {
        //获取用户信息必须从数据库查询，
        AuthUserDO currentUser = userService.getById(ContextUtils.getUserID());
        currentUser.getAuthRoles().addAll(authRoleService.getRolesByUserId(currentUser.getId()));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("authUser", currentUser);
        map.put("permission", permissionService.getUserPermissionList(currentUser.getId()));
        return ResultUtils.success(map);
    }

    @ApiOperation("编辑当前用户信息")
    @PostMapping("editCurrentUser")
    @ResponseBody
    public Result<AuthUserDO> editCurrentUser(@RequestBody UserEditDto authUser) {
        AuthUserDO currentUser = userService.getById(ContextUtils.getUserID());
        AuthUserDO authUserRes = userService.editCurrentUser(authUser);
        authUserRes.getAuthRoles().addAll(authRoleService.getRolesByUserId(currentUser.getId()));
        return ResultUtils.success(authUserRes);
    }

    @ApiOperation("编辑当前用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "原密码", required = true),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true),
    })
    @PostMapping("editCurrentUserPassword")
    @ResponseBody
    public Result<AuthUserDO> editCurrentUserPassword(String oldPassword, String newPassword) {
        if (StringUtils.isEmpty(oldPassword)) {
            throw new ServiceException("请输入原密码");
        }
        if (StringUtils.isEmpty(newPassword)) {
            throw new ServiceException("请输入新密码");
        }
        AuthUserDO currentUser = userService.getById(ContextUtils.getUserID());

        userService.editCurrentUserPassword(currentUser.getId(), oldPassword, newPassword);
        return ResultUtils.success();
    }

    @ApiOperation("管理员编辑用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名", required = false),
            @ApiImplicitParam(name = "nikeName", value = "姓名", required = false),
            @ApiImplicitParam(name = "avatar", value = "头像", required = false),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码", required = false),
            @ApiImplicitParam(name = "passWord", value = "密码", required = false),
            @ApiImplicitParam(name = "roleIds", value = "角色列表", required = false, dataTypeClass = Long.class, allowMultiple = true),
    })
    @PostMapping("editUser")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:user:edit')")
    public Result<AuthUserDO> editUser(Long id, String userName, String nikeName, String avatar, String phoneNumber, String passWord, Long[] roleIds) {
        if (id == null || id <= 0) {
            throw new ServiceException("用户ID不正确");
        }
        AuthUserDO authUser = userService.editUser(id, userName, nikeName, avatar, phoneNumber, passWord, roleIds);
        authUser.getAuthRoles().addAll(authRoleService.getRolesByUserId(id));
        return ResultUtils.success(authUser);
    }

    @ApiOperation("删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true),
    })
    @PostMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('system:user:delete')")
    public Result delete(Long id) {
        userService.delete(id);
        return ResultUtils.success();
    }

}
