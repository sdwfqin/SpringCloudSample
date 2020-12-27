package com.sdwfqin.cloudsample.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 激活Eureka中的DiscoveryClient实现
@MapperScan("com.sdwfqin.cloudsample.*.mapper")
@EnableFeignClients(basePackages = {"com.sdwfqin.cloudsample.*"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.sdwfqin.cloudsample.*"})
public class SimpleClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleClientApplication.class, args);
    }

}
