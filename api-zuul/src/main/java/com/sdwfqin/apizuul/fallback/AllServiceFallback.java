package com.sdwfqin.apizuul.fallback;

import com.sdwfqin.common.result.Result;
import com.sdwfqin.common.result.ResultEnum;
import com.sdwfqin.common.result.ResultUtils;
import com.sdwfqin.common.utils.GsonUtils;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

// 添加注解#Component使其成为Spring管理的bean
@Component
public class AllServiceFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        // 为哪个微服务提供提供回退服务，返回微服务的名字,必须和注册在Eureka Server上的名字一致
        // return "microservice-provider-user";
        // 为全部微服务提供回退服务
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        // fallback时的状态码
//        if (cause instanceof HystrixTimeoutException) {
//            return response(HttpStatus.GATEWAY_TIMEOUT);
//        } else {
//            return response(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return response(HttpStatus.OK);
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                // 响应体
                Result<Object> objectResult = ResultUtils.errorData(ResultEnum.SERVICE_ERROR);
                return new ByteArrayInputStream((GsonUtils.beanToString(objectResult)).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设置
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
