package com.itplh.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author: tanpenggood
 * @date: 2020-10-26 22:35
 */
@Slf4j
@Component
public class MyJobListener implements JobExecutionListener {
    private long start;
    private long end;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        start = System.currentTimeMillis();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        end = System.currentTimeMillis();
        log.info("任务处理结束，耗时：{}毫秒", end - start);
    }
}
