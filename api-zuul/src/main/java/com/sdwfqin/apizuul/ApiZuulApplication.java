package com.sdwfqin.apizuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ApiZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiZuulApplication.class, args);
    }

}
