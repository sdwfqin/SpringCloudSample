package com.sdwfqin.eurekaconsumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 通过LoadBalancerClient接口来获取某个服务的具体实例
 * <p>
 *
 * @author 张钦
 * @date 2019/10/31
 */
@Slf4j
@RequestMapping("/lbc")
@RestController
public class LbcController {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过loadBalancerClient的choose函数来负载均衡的选出一个eureka-client的服务实例,
     * 这个服务实例的基本信息存储在ServiceInstance中，然后通过这些对象中的信息拼接出访问/dc接口的详细地址，
     * 最后再利用RestTemplate对象实现对服务提供者接口的调用。
     */
    @GetMapping("/consumer")
    public String dc() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        log.info(url);
        return restTemplate.getForObject(url, String.class);
    }
}
