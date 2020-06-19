package com.itplh.chapter4.section3SpringBoot的配置.point3外部配置;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * 在IDEA中配置program arguments：
 * --args='--server.port=${random.int[1024, 10000]} --server.ip=192.168.0.87 --some.value=${random.value} --some.number=${random.int}'
 * @author: tanpenggood
 * @since: 2020-06-07 00:44
 */
@Configuration("com.itplh.section3SpringBoot的配置.point3外部配置.Usage3RandomValuePropertySource随机值")
public class Usage3RandomValuePropertySource随机值 {

    @Value("${server.ip:127.0.0.1}")
    private String serverIp;

    @Value("${some.value:-1}")
    private String someValue;

    @Value("${some.number:-1}")
    private String someNumber;

    @Bean("com.itplh.section3SpringBoot的配置.point3外部配置.Usage3RandomValuePropertySource随机值.commandLineRunner")
    public CommandLineRunner commandLineRunner(@Value("${server.port:8080}") String serverPort) {
        return args -> {
            Stream.of(args).forEach(System.out::println);
            System.out.println(serverIp);
            System.out.println(serverPort);
            System.out.println(someValue);
            System.out.println(someNumber);
        };
    }
}
