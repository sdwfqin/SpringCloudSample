package com.sdwfqin.serviceauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 权限测试用
 * <p>
 *
 * @author 张钦
 * @date 2019/11/1
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
