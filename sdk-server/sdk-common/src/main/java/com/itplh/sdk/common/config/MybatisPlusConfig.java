package com.itplh.sdk.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:59
 */
@Configuration
@MapperScan(value={"com.itplh.sdk.**.mapper*"})
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    @Profile("dev")
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

}
