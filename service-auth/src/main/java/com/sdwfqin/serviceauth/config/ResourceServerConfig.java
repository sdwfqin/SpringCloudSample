package com.sdwfqin.serviceauth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdwfqin.commonutils.result.ResultEnum;
import com.sdwfqin.commonutils.result.ResultUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //设置资源服务器id,需要与认证服务器对应
        resources.resourceId("service-auth");
        //当权限不足时返回
        resources.accessDeniedHandler((request, response, e) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ResultUtils.errorData(ResultEnum.AUTHORITY_ERROR)));
        });
        //当token不正确时返回
        resources.authenticationEntryPoint((request, response, e) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ResultUtils.errorData(ResultEnum.TOKEN_ERROR)));
        });
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置哪些请求需要验证
        http.csrf().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, exception) -> {
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    resp.getWriter()
                            .write(objectMapper.writeValueAsString(ResultUtils.errorData(ResultEnum.TOKEN_ERROR)));
                })
                .and()
                // 无需登陆
                .authorizeRequests()
                .antMatchers("/user/register")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
        //拦截所有请求,并且检查sope
        // .authorizeRequests().anyRequest().access("isAuthenticated() && #oauth2.hasScope('app')");
    }
}