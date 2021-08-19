package com.sdwfqin.cloudsample.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis plus 配置
 * <p>
 *
 * @author 张钦
 * @date 2020/12/27
 */
@Configuration
@Slf4j
public class MybatisPlusConfig {

    /**
     * MybatisPlus 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * 自动填充
     */
    @Component
    public static class AutoFillComponent implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
            this.strictInsertFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        }
    }

}
