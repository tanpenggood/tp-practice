package com.itplh.chapter4.section3SpringBoot的配置.point1应用配置;

import com.itplh.chapter4.Chapter4Application;
import org.springframework.boot.SpringApplication;

/**
 * 通过外部配置（可以是命令行、系统环境变量或application.properties）来定制应用启动行为
 *
 * @author: tanpenggood
 * @since: 2020-06-06 23:49
 */
public class Usage3通过外部配置 {

    public static void main(String[] args) {
        SpringApplication.run(Chapter4Application.class, args);
    }
}
