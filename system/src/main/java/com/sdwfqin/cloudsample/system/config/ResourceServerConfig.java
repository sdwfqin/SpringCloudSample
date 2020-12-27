package com.sdwfqin.cloudsample.system.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器认证配置已经过时
 * https://mp.weixin.qq.com/s?__biz=MzI1NDY0MTkzNQ==&mid=2247490016&idx=1&sn=88e9dd6589d0b9e31bc066cc4a3176c9&chksm=e9c34b80deb4c296a82340967b11e6690c94f4b99ca9b213f654febfe27d91eeaffa360c0ffe&scene=178&cur_album_id=1319828555819286528#rd
 */
@Slf4j
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //关闭跨站请求防护
        http.csrf().disable();
        //前后端分离登陆成功处理
        http.authorizeRequests()
                // 放行start
                .antMatchers("/admin/**").permitAll()
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
