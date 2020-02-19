package com.aden.command;

import com.zyh.ce4j.executor.BaseExecutor;
import com.zyh.ce4j.strategy.ColonEndStrategy;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 12:03
 * @version: 1.0.0
 */
public class CommandExecutor {

    public static BaseExecutor executor;

    static {
        executor = BaseExecutor.newBuilder()
                .useStdoutStreamGobbler(true, new ColonEndStrategy())
                .collectAllOutputStdout(true)
                .build();
    }
}
