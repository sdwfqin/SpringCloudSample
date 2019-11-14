//package com.sdwfqin.eurekaconsumer.service;
//
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.sdwfqin.commonutils.result.Result;
//import com.sdwfqin.commonutils.result.ResultEnum;
//import com.sdwfqin.commonutils.result.ResultUtils;
//import com.sdwfqin.eurekaconsumer.dc.DcClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
///**
// * 服务降级
// * <p>
// *
// * @author 张钦
// * @date 2019/11/1
// */
//@Service
//public class HystrixConsumerService {
//
//    // @Autowired
//    // RestTemplate restTemplate;
//    @Autowired
//    DcClient dcClient;
//
//    /**
//     * 服务降级、依赖隔离、断路器
//     */
//    @HystrixCommand(fallbackMethod = "helloFallBack")
//    public Result consumer() {
//        return restTemplate.getForObject("http://eureka-client/dc", Result.class);
//    }
//
//    public Result helloFallBack() {
//        return ResultUtils.errorData(ResultEnum.SERVICE_ERROR);
//    }
//}
