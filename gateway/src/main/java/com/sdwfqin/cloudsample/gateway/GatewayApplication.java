package com.sdwfqin.cloudsample.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关
 * http://www.ityouknow.com/springcloud/2018/12/12/spring-cloud-gateway-start.html
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ GatewayApplication服务启动成功 ヾ(◍°∇°◍)ﾉﾞ\n");
    }

}
