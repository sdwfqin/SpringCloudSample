package com.sdwfqin.serviceauth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdwfqin.common.result.ResultEnum;
import com.sdwfqin.common.result.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //当权限不足时返回
        resources.accessDeniedHandler((request, response, e) -> {
            log.error("【accessDeniedHandler】{}", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            // 统一认证失败返回的异常
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ResultUtils.errorData(ResultEnum.AUTHORITY_ERROR)));
        });
        //当token不正确时返回
        resources.authenticationEntryPoint((request, response, e) -> {
            log.error("【authenticationEntryPoint】{}", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(ResultUtils.errorData(ResultEnum.TOKEN_ERROR)));
        });
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置哪些请求需要验证
        http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                // 放行start
                .antMatchers("/user/register")
                .permitAll()
                // 放行end
                // ==========
                // 认证start
                .anyRequest()
                .authenticated();
    }
}