package com.itplh.juc.delayqueue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-15 16:48
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
public class DelayTask implements Delayed {

    private String taskName;
    private long time;

    public DelayTask(String taskName, long time, TimeUnit unit) {
        this.taskName = taskName;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return this.time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof DelayTask) {
            DelayTask task = (DelayTask) o;
            long diff = this.getTime() - task.getTime();
            if (diff > 0) {
                return 1;
            }
        }
        return -1;
    }
}
