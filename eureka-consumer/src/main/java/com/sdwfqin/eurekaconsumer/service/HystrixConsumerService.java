package com.sdwfqin.eurekaconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 服务降级
 * <p>
 *
 * @author 张钦
 * @date 2019/11/1
 */
@Service
public class HystrixConsumerService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 服务降级、依赖隔离、断路器
     */
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String consumer() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }

    public String helloFallBack() {
        return "error";
    }
}
