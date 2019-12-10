package com.sdwfqin.eurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 使用@EnableCircuitBreaker或@EnableHystrix注解开启Hystrix的使用
// @EnableCircuitBreaker
// 通过@EnableFeignClients注解开启扫描Spring Cloud Feign客户端的功能
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.sdwfqin.eurekaconsumer.*", "com.sdwfqin.common.*"})
public class EurekaConsumerApplication {

//    /**
//     * 初始化RestTemplate，用来真正发起REST请求
//     * ribbon与LoadBalancerClient只能二选一，ribbon可以与feign共存
//     *
//     * TODO: 这里是给ribbon或LoadBalancerClient用的
//     */
//    @Bean
//    // 这个注解是给ribbon用的
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

}
