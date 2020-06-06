package com.itplh.section8;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 15:12
 */
@Configuration
public class JavaConfig {

    @Bean
    public CommandLineRunner commandLineRunner(DemoLoggerService demoLoggerService) {
        return args -> demoLoggerService.doSomething();
    }

}
