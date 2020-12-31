package com.sdwfqin.cloudsample.simple.controller;

import com.sdwfqin.cloudsample.common.annotation.Log;
import com.sdwfqin.cloudsample.common.result.Result;
import com.sdwfqin.cloudsample.common.result.ResultEnum;
import com.sdwfqin.cloudsample.common.result.ResultUtils;
import com.sdwfqin.cloudsample.common.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class Test {

    @Log("test")
    @GetMapping("/test")
    public Result test(HttpServletRequest request){
        log.info(ContextUtils.getUserID() + "");
        return ResultUtils.resultData(ResultEnum.SUCCESS, "hello");
    }
}
