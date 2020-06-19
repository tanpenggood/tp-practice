package com.itplh.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author: tanpeng
 * @since: 2020-06-19 10:49
 */
@Slf4j
@RestController
@RequestMapping("/async/callable")
public class AsyncCallableController {

    @GetMapping
    public Callable<String> callable() {
        log.info("主线程开始...");
        Callable<String> callable = () -> {
            log.info("副线程开始...");
            Thread.sleep(1000);
            log.info("副线程结束...");
            return "hello callable";
        };
        log.info("主线程结束...");
        return callable;
    }

}
