package com.sdwfqin.cloudsample.auth.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdwfqin.cloudsample.common.entity.AuthUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
public interface AuthUserMapper extends BaseMapper<AuthUserDO> {
    IPage<AuthUserDO> getUserList(Page<?> page, Long roleId);

    /**
     * 获取用户简略信息
     * @param id
     * @return
     */
    AuthUserDO getUserVo(Long id);

}
