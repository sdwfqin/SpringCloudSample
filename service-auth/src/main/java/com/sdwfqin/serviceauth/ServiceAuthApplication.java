package com.sdwfqin.serviceauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("com.sdwfqin.serviceauth.dao")
@SpringBootApplication(scanBasePackages = {"com.sdwfqin.serviceauth.*", "com.sdwfqin.common.*"})
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }

}
