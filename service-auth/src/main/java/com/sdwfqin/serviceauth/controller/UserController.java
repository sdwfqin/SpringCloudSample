package com.sdwfqin.serviceauth.controller;

import com.sdwfqin.commonutils.result.Result;
import com.sdwfqin.commonutils.result.ResultEnum;
import com.sdwfqin.commonutils.result.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(principal.toString());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    @GetMapping("/register")
    public Result register() {
        return ResultUtils.resultData(ResultEnum.SUCCESS, "注册");
    }
}