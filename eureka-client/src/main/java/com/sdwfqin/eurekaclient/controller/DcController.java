package com.sdwfqin.eurekaclient.controller;

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
    public String dc() throws Exception {
        // TODO 测试服务容错（降级）
        // Thread.sleep(5000L);
        String services = "Services: " + mDiscoveryClient.getServices();
        log.info(services);
        return "eureka-client返回的数据：" + services;
    }
}
