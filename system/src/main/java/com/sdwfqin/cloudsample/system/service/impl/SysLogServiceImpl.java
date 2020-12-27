package com.sdwfqin.cloudsample.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdwfqin.cloudsample.common.entity.SysLogDO;
import com.sdwfqin.cloudsample.system.mapper.SysLogMapper;
import com.sdwfqin.cloudsample.system.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author zhangqin
 * @since 2020-12-16
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogDO> implements ISysLogService {

}
