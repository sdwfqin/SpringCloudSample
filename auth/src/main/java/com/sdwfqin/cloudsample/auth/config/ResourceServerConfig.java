package com.sdwfqin.cloudsample.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置哪些请求需要验证
        http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                // 放行start
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/instances/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v3/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                // 放行end
                // ==========
                // 认证start
                .anyRequest()
                .authenticated();
    }
}