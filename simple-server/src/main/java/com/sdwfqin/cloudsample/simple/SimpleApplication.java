package com.sdwfqin.cloudsample.simple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 使用@EnableCircuitBreaker或@EnableHystrix注解开启Hystrix的使用
// @EnableCircuitBreaker
// 通过@EnableFeignClients注解开启扫描Spring Cloud Feign客户端的功能
@MapperScan("com.sdwfqin.cloudsample.*.mapper")
@EnableFeignClients(basePackages = {"com.sdwfqin.cloudsample.*"})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.sdwfqin.cloudsample.*"})
public class SimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ SimpleApplication服务启动成功 ヾ(◍°∇°◍)ﾉﾞ\n");
    }

}
