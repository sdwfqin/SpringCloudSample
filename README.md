# Spring Cloud入门

![sample](/file/service_v1.0.png)

## module描述

1. common 公共工具
2. eureka 服务注册
3. ~~api-zuul~~ 服务网关
4. simple-client 服务提供者（微服务）
5. simple-consumer 服务消费者（微服务）
6. auth 认证服务
7. gateway 服务网关
8. system 系统服务

## 版本信息

1. java 11
2. spring-boot 2.2.12.RELEASE
3. spring-cloud Hoxton.SR9

## 前端代码

[~~SpringCloudSampleAdminWeb~~](https://github.com/sdwfqin/SpringCloudSampleAdminWeb) 近期准备新升级

## 已添加内容

1. eureka 注册中心
2. ~~zuul~~ 网关中心
3. ribbon 负载均衡，获取服务提供者地址
4. openfeign 声明式服务调用（简化ribbon）
5. Hystrix 服务容错保护
6. Oauth2.0 认证授权（使用redis作为缓存）
7. Aop拦截打印请求日志
8. 全局异常处理
9. SpringAdmin
10. gateway 网关中心
11. druid 数据库连接池
12. mybatis-plus 
13. Swagger
14. 简单的角色权限管理

## 集成后台访问地址

1. Swagger
    - http://127.0.0.1:${port}/swagger-ui/
2. SpringAdmin
    - http://127.0.0.1:8008/admin/
3. Eureka
    - http://127.0.0.1:8001/
4. Druid
    - http://127.0.0.1:${port}/druid/
