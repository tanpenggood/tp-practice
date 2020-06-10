package com.itplh.modules.test;

import com.itplh.common.Result;
import com.itplh.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:59
 */
@RestController
public class TestController {

    @Autowired
    private IdWorker idWorker;

    @GetMapping("id")
    public Result id() {
        String[] array = {
                idWorker.nextId() + "",
                idWorker.nextId() + "",
                idWorker.nextId() + "",
                idWorker.nextId() + "",
                idWorker.nextId() + ""
        };
        return Result.ok(array);
    }

}
