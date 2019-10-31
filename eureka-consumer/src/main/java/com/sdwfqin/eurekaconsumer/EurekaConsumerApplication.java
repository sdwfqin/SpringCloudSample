package com.sdwfqin.eurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 通过@EnableFeignClients注解开启扫描Spring Cloud Feign客户端的功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerApplication {

    /**
     * 初始化RestTemplate，用来真正发起REST请求
     * ribbon与LoadBalancerClient只能二选一，ribbon可以与feign共存
     *
     * TODO: 这里是给ribbon或LoadBalancerClient用的
     */
    @Bean
    // 这个注解是给ribbon用的
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

}
