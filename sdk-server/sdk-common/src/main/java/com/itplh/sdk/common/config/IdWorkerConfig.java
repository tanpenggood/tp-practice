package com.itplh.sdk.common.config;

import com.itplh.sdk.common.util.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tanpeng
 * @since: 2020-06-10 11:39
 */
@Configuration
public class IdWorkerConfig {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1,1);
    }

}
