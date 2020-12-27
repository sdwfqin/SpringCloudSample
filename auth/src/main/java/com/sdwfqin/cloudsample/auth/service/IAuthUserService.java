package com.sdwfqin.cloudsample.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdwfqin.cloudsample.auth.dto.UserDto;
import com.sdwfqin.cloudsample.auth.dto.UserEditDto;
import com.sdwfqin.cloudsample.common.entity.AuthUserDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
public interface IAuthUserService extends IService<AuthUserDO> {

    /**
     * 根据用户名获取用户信息
     *
     * @param name
     * @return
     */
    AuthUserDO getUserDataByName(String name);

    /**
     * 添加用户
     */
    AuthUserDO addUser(UserDto userDto);

    /**
     * 获取用户列表
     *
     * @return
     */
    IPage<AuthUserDO> list(Integer pageNum, Integer pageSize, Long roleId);

    /**
     * 编辑当前用户
     *
     * @return
     */
    AuthUserDO editCurrentUser(UserEditDto UserDOEditDto);

    /**
     * 修改当前用户密码
     *
     * @return
     */
    void editCurrentUserPassword(Long uesrId, String oldPassword, String newPassword);

    /**
     * 管理员编辑用户信息
     *
     * @return
     */
    AuthUserDO editUser(Long id, String userName, String nikeName, String avatar, String phoneNumber, String passWord, Long[] roleIds);

    /**
     * 删除用户
     *
     * @return
     */
    void delete(Long id);
}
