package com.sdwfqin.cloudsample.apizuul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableZuulProxy
@MapperScan("com.sdwfqin.cloudsample.*.mapper")
@EnableFeignClients(basePackages = {"com.sdwfqin.cloudsample.*"})
@EnableEurekaClient
@SpringBootApplication()
@Deprecated
public class ApiZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiZuulApplication.class, args);
    }
}
