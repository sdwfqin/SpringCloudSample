package com.sdwfqin.cloudsample.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.sdwfqin.cloudsample.*.mapper")
@EnableFeignClients(basePackages = {"com.sdwfqin.cloudsample.*"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.sdwfqin.cloudsample.*"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
