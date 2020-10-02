package com.itplh.gc;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author: tanpenggood
 * @date: 2020-10-03 00:16
 */
public class G1gcTest {

    public static void main(String[] args) {
        // -Xms20m -Xmx20m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
        oom();
    }

    /**
     * -Xms20m -Xmx20m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
     *
     * @author: tanpenggood
     * @date: 2020-10-03 00:42
     */
    private static void oom() {
        ArrayList list = new ArrayList();
        new Thread(() -> {
            for (; ; ) {
                list.add(new byte[100 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }

}
