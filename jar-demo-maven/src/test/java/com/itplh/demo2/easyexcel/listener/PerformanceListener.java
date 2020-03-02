package com.itplh.demo2.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-02 22:19
 * @version: v1.0.0
 */
public class PerformanceListener extends AnalysisEventListener<Object> {

    private long startTime = System.currentTimeMillis();

    @Override
    public void invoke(Object data, AnalysisContext context) {
        if (context.readRowHolder().getRowIndex() % 10000 == 0) {
            System.out.println("read row +10000");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("use time " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
