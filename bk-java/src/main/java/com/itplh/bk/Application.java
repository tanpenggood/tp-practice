package com.itplh.bk;

import com.itplh.bk.service.BkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-10 10:36
 * @version: v1.0.0
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final String URL = "https://cq.ke.com/ershoufang/nanan/";
    private static final String DAN_ZI_SHI_INDEX = "https://cq.ke.com/ershoufang/danzishi/";
    private static final String DAN_ZI_SHI_TEMPLATE = "https://cq.ke.com/ershoufang/danzishi/%s/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private BkService bkService;

    @Override
    public void run(String... args) throws Exception {
        bkService.simpleSpider(DAN_ZI_SHI_INDEX, DAN_ZI_SHI_TEMPLATE);
    }

}
