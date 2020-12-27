package com.sdwfqin.cloudsample.auth.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.security.auth.AuthPermission;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<AlternateTypeRule> alternateTypeRules = Lists.newArrayList();
        TypeResolver typeResolver = new TypeResolver();
        // 防止无限调用
        alternateTypeRules.add(new AlternateTypeRule(typeResolver.resolve(AuthPermission.class), typeResolver.resolve(Object.class)));
        return new Docket(DocumentationType.OAS_30)
//                .groupName("all 全部接口")
                .apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
//                .alternateTypeRules(alternateTypeRules.toArray(new AlternateTypeRule[alternateTypeRules.size()]))
                .select()

                //为当前包路径
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("用户中心接口文档")
                //创建人
                .contact(new Contact("zhangqin", "", ""))
                //版本号
                .version("1.0.0")
                //描述
                .description("用户中心接口文档")
                .build();
    }
}
