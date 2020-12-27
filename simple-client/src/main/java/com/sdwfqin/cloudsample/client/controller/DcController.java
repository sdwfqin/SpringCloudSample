package com.sdwfqin.cloudsample.client.controller;

import com.sdwfqin.cloudsample.common.result.Result;
import com.sdwfqin.cloudsample.common.result.ResultEnum;
import com.sdwfqin.cloudsample.common.result.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DcController {

    @Autowired
    DiscoveryClient mDiscoveryClient;

    @GetMapping("/dc")
    public Result dc() throws Exception {
        // 发现服务
        String services = "Services: " + mDiscoveryClient.getServices();
        log.info(services);
        return ResultUtils.resultData(ResultEnum.SUCCESS, "eureka-client返回的数据：" + services);
    }
}
