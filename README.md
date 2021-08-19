# Spring Cloud入门

![sample](/file/service_v1.0.png)

## module描述

1. common 公共工具
2. auth 认证服务
3. gateway 服务网关
4. system 系统服务

## 注意⚠️

1. 需要预先安装nacos、mysql、redis
2. mysql需要导入数据库[cloud_sample](/sql/cloud_sample.sql)

## 版本信息

1. spring-boot 2.4.2
2. spring-cloud 2020.0.1
3. spring-cloud-alibaba 2021.1

## 前端代码

[~~SpringCloudSampleAdminWeb~~](https://github.com/sdwfqin/SpringCloudSampleAdminWeb) 近期准备新升级

## 已添加内容

1. ribbon 负载均衡，获取服务提供者地址
2. openfeign 声明式服务调用（简化ribbon）
3. Hystrix 服务容错保护
4. Oauth2.0 认证授权（使用redis作为缓存）
5. Aop拦截打印请求日志
6. 全局异常处理
7. SpringAdmin
8. gateway 网关中心
9. druid 数据库连接池
10. mybatis-plus 
11. Swagger
12. 简单的角色权限管理

## 集成后台访问地址

1. Swagger
    - http://127.0.0.1:${port}/swagger-ui/
2. SpringAdmin
    - http://127.0.0.1:8008/admin/
3. Druid
    - http://127.0.0.1:${port}/druid/
