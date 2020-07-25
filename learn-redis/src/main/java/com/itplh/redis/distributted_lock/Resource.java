package com.itplh.redis.distributted_lock;

import com.itplh.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tanpenggood
 * @date: 2020-07-25 20:08
 * @since: java 8
 */
@RestController
@RequestMapping("/resource")
public class Resource {

    // 模拟商品总数
    public static int count = 100;

    @GetMapping
    public Result restResource(@RequestParam(required = false, defaultValue = "100") Integer c) {
        count = c;
        return Result.ok();
    }

}
