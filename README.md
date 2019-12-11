# Spring Cloud入门

![sample](/file/service_v1.0.png)

## module描述

1. common-utils 公共工具
2. eureka-service 服务注册
3. api-zuul 服务网关
4. eureka-client 服务提供者（微服务）
5. eureka-consumer 服务消费者（微服务）
6. service-auth 认证服务

## 版本信息

1. java 1.8
2. spring-boot 2.1.9.RELEASE
3. spring-cloud Greenwich.SR3

## Postman测试文件

[查看接口](/file/SpringCloudSample.postman_collection.json)

## 已添加内容

1. eureka 注册中心
2. zuul 网关中心
3. ribbon 负载均衡，获取服务提供者地址
4. feign 声明式服务调用（简化ribbon）
5. Hystrix 服务容错保护
6. Oauth2.0 认证授权（使用redis作为缓存）
7. Aop拦截打印请求日志
8. 全局异常处理