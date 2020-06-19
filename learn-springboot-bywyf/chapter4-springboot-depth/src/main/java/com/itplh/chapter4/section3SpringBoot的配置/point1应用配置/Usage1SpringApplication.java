package com.itplh.chapter4.section3SpringBoot的配置.point1应用配置;

import com.itplh.chapter4.Chapter4Application;
import com.itplh.chapter4.listener.MyListener;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 23:49
 */
public class Usage1SpringApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Chapter4Application.class);
        // 设置关闭Banner
        app.setBannerMode(Banner.Mode.OFF);
        // 增加监听器
        app.addListeners(new MyListener());
        app.run(args);
    }
}
