package com.sdwfqin.cloudsample.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdwfqin.cloudsample.common.entity.SysLogDO;
import com.sdwfqin.cloudsample.common.result.Result;
import com.sdwfqin.cloudsample.common.result.ResultUtils;
import com.sdwfqin.cloudsample.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author zhangqin
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/log")
public class SysLogController {
    @Autowired
    ISysLogService logService;

    @GetMapping()
    Result list(Integer pageNum, Integer pageSize) {
        Page<SysLogDO> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SysLogDO> queryWrapper = new QueryWrapper<SysLogDO>().lambda()
                .orderByDesc(SysLogDO::getGmtCreate);
        IPage<SysLogDO> list = logService.page(page, queryWrapper);
        return ResultUtils.success(list);
    }

    @PostMapping("/save")
    Result save(@RequestBody SysLogDO logDO) {
        if (logService.save(logDO)) {
            return ResultUtils.success();
        }
        return ResultUtils.errorDefault();
    }

    @DeleteMapping()
    Result remove(Long id) {
        if (logService.removeById(id)) {
            return ResultUtils.success();
        }
        return ResultUtils.errorDefault();
    }

    @PostMapping("/batchRemove")
    Result batchRemove(@RequestParam("ids[]") Long[] ids) {
        if (logService.removeByIds(Arrays.asList(ids))) {
            return ResultUtils.success();
        }
        return ResultUtils.errorDefault();
    }

}
