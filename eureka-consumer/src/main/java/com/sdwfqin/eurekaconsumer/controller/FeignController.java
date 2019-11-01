package com.sdwfqin.eurekaconsumer.controller;

import com.sdwfqin.eurekaconsumer.dc.DcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 通过Feign来获取某个服务的具体实例
 * <p>
 *
 * @author 张钦
 * @date 2019/10/31
 */
@Slf4j
@RequestMapping("/feign")
@RestController
public class FeignController {

    @Autowired
    DcClient dcClient;

    @GetMapping("/consumer")
    public String consumer() {
        return dcClient.consumer();
    }
}
