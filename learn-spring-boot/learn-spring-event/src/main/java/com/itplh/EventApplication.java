package com.itplh;

import com.itplh.event.CustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EventApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/tigger-event")
    public String tigger() {
        Result result;
        double random = Math.random() * 10;
        System.out.println(random);
        if (random > 7) {
            result = new Result("1", true, "加10分");
        } else if (random > 4) {
            result = new Result("2", true, "加20分");
        } else if (random > 1) {
            result = new Result("3", true, "扣10分");
        } else {
            result = new Result("4", false, "未知错误");
        }
        // 发布事件
        applicationEventPublisher.publishEvent(new CustomEvent(result));
        return result.toString();
    }
}
