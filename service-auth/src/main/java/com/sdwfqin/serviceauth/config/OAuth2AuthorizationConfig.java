package com.sdwfqin.serviceauth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdwfqin.serviceauth.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Slf4j
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private UserDetailsServiceImpl userDetailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("sdwfqin");

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 将客户端的信息存储在内存中
        clients.inMemory()
                // 创建了一个client名为android的客户端
                .withClient("android")
                .secret(finalSecret)
                // .resourceIds("service-auth")
                // 配置验证类型
                .authorizedGrantTypes("password", "refresh_token")
                // 配置客户端域
                .scopes("mobile")
                .and()
                .withClient("service")
                .secret(finalSecret)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置Token的存储方式
        endpoints
                // 读取用户的验证信息
                .userDetailsService(userDetailService)
                // 注入WebSecurityConfig配置的bean
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .tokenServices(redisTokenServices());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许表单认证
                .allowFormAuthenticationForClients()
                // 对获取Token的请求不再拦截
                .tokenKeyAccess("permitAll()")
                // 验证获取Token的验证信息
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public DefaultTokenServices redisTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}