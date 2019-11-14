//package com.sdwfqin.eurekaconsumer.controller;
//
//import com.sdwfqin.commonutils.result.Result;
//import com.sdwfqin.eurekaconsumer.service.HystrixConsumerService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 带有服务降级的
// * <p>
// *
// * @author 张钦
// * @date 2019/10/31
// */
//@Slf4j
//@RequestMapping("/hystrix")
//@RestController
//public class HystrixController {
//
//    @Autowired
//    HystrixConsumerService mHystrixConsumerService;
//
//    @GetMapping("/consumer")
//    public Result consumer() {
//        return mHystrixConsumerService.consumer();
//    }
//
//}
