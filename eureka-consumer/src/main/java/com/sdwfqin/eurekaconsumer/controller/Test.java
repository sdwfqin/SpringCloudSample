package com.sdwfqin.eurekaconsumer.controller;

import com.sdwfqin.common.result.Result;
import com.sdwfqin.common.result.ResultEnum;
import com.sdwfqin.common.result.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Test {

    @GetMapping("/test")
    public Result test(HttpServletRequest request){
        return ResultUtils.resultData(ResultEnum.SUCCESS,"hhh");
    }
}
