package com.sdwfqin.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// 激活Eureka中的DiscoveryClient实现
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.sdwfqin.eurekaclient.*", "com.sdwfqin.common.*"})
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
