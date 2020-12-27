package com.sdwfqin.cloudsample.common.intercepter;

import com.sdwfqin.cloudsample.common.constants.CommonConstants;
import com.sdwfqin.cloudsample.common.utils.ContextUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("------feign设置token" + Thread.currentThread().getId());
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, ContextUtils.getToken());
    }
}