package com.aden.command;

import com.zyh.ce4j.executor.BaseExecutor;
import com.zyh.ce4j.executor.Executor;
import com.zyh.ce4j.strategy.CheckStrategy;
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
        // 插件默认：使用Stdout输出流，不使用错误输出流，不收集命令行输出，不判定执行结果（即使用空判定策略）
        executor = BaseExecutor.newBuilder().build();

		// 或者（效果同上，此为插件默认配置,完整版）：
//		executor = BaseExecutor
//							.newBuilder()
//							.useStdoutStreamGobbler(true, CheckStrategy.NONE_CHECK_STRATEGY_INSTANCE)
//							.collectAllOutputStdout(false)
//							.build();

        // 自定义执行结果判定策略、自定义使用流（stdoutStream or errorStream）、定义是否收集命令行输出
//        executor = BaseExecutor.newBuilder()
//                //使用StdoutStream,使用自定义执行结果判定状态,ColonEndStrategy为插件实现的一种场景的判定策略,自定义判定策略请参照ColonEndStrategy的实现
//                .useStdoutStreamGobbler(true, new ColonEndStrategy())
//                .collectAllOutputStdout(true)//收集命令行输出
//                .build();
    }
}
