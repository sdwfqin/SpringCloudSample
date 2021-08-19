package com.sdwfqin.cloudsample.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.sdwfqin.cloudsample.*.mapper")
@EnableFeignClients(basePackages = {"com.sdwfqin.cloudsample.*"})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.sdwfqin.cloudsample.*"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ AuthApplication服务启动成功 ヾ(◍°∇°◍)ﾉﾞ\n");
    }

}
