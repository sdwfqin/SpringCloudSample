package com.sdwfqin.cloudsample.common.service;

import com.sdwfqin.cloudsample.common.entity.SysLogDO;
import com.sdwfqin.cloudsample.common.intercepter.FeignInterceptor;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "system", configuration = FeignInterceptor.class)
public interface LogRpcService {

    @Async
    @PostMapping("/log/save")
    void save(SysLogDO logDO);
}
