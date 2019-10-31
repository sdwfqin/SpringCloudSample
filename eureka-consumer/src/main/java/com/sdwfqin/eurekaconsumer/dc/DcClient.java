package com.sdwfqin.eurekaconsumer.dc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 创建一个Feign的客户端接口定义。
 * 使用@FeignClient注解来指定这个接口所要调用的服务名称，
 * 接口中定义的各个函数使用Spring MVC的注解就可以来绑定服务提供方的REST接口
 * <p>
 *
 * @author 张钦
 * @date 2019/10/31
 */
@FeignClient("eureka-client")
public interface DcClient {

    @GetMapping("/dc")
    String consumer();

}
