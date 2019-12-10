package com.sdwfqin.serviceauth.controller;

import com.sdwfqin.common.result.Result;
import com.sdwfqin.common.result.ResultUtils;
import com.sdwfqin.serviceauth.domain.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @GetMapping("/userInfo")
    public Result getUserInfo() {
        UserDo userDo = (UserDo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", userDo.getId());
        map.put("username", userDo.getUsername());
        map.put("nickName", userDo.getNickName());
        return ResultUtils.success(map);
    }

    @GetMapping("/register")
    public Result register() {
        return ResultUtils.success("注册");
    }
}