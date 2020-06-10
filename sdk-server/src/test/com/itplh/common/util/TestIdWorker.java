package com.itplh.common.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIdWorker {

    @Autowired
    private IdWorker idWorker;

    @Test
    public void snowflakeId() {
        for (int i = 0; i < 10; i++) {
            System.out.println(idWorker.nextId());
        }
    }

}
