package com.itplh.section3SpringBoot的配置.point1应用配置;

import com.itplh.Application;
import com.itplh.listener.MyListener;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 23:49
 */
public class Usage1SpringApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        // 设置关闭Banner
        app.setBannerMode(Banner.Mode.OFF);
        // 增加监听器
        app.addListeners(new MyListener());
        app.run(args);
    }
}
