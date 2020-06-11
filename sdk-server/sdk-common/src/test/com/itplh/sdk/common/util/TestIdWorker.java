package com.itplh.sdk.common.util;

import org.junit.Test;

public class TestIdWorker {

    @Test
    public void snowflakeId() {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        for (int i = 0; i < 10; i++) {
            System.out.println(idWorker.nextId());
        }
    }

}
