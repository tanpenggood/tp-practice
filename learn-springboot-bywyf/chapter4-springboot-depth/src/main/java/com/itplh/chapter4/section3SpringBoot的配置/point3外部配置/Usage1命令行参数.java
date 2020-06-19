package com.itplh.chapter4.section3SpringBoot的配置.point3外部配置;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * 打包后执行命令
 * java -jar chapter4-springboot-depth-1.0.0.jar --server.port=8888 --server.ip=192.168.0.87
 * @author: tanpenggood
 * @since: 2020-06-07 00:44
 */
@Configuration("com.itplh.section3SpringBoot的配置.point3外部配置.Usage1命令行参数")
public class Usage1命令行参数 {

    @Value("${server.ip:127.0.0.1}")
    private String serverIp;

    @Bean("com.itplh.section3SpringBoot的配置.point3外部配置.Usage1命令行参数.commandLineRunner")
    public CommandLineRunner commandLineRunner(@Value("${server.port:8080}") String serverPort) {
        return args -> {
            Stream.of(args).forEach(System.out::println);
            System.out.println(serverIp);
            System.out.println(serverPort);
        };
    }
}
