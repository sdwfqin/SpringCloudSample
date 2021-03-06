package com.sdwfqin.cloudsample.simple.config;

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
        //关闭跨站请求防护
        http.csrf().disable();
        //前后端分离登陆成功处理
        http.authorizeRequests()
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
                .authenticated()
                .and()
                .cors()
                .and()
                .formLogin().disable()
                .httpBasic().disable();
    }
}