package com.itplh.chapter4.section3SpringBoot的配置.point1应用配置;

import com.itplh.chapter4.Chapter4Application;
import com.itplh.chapter4.listener.MyListener;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 23:49
 */
public class Usage2SpringApplicationBuilder {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .listeners(new MyListener())
                .sources(Chapter4Application.class)
                .build(args)
                .run();
    }
}
