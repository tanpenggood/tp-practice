package com.itplh.demo2.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.itplh.demo2.easyexcel.read.ConverterData;
import lombok.Data;

/**
 * 解析监听器，
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 * @description:
 * @author: tanpeng
 * @date: 2020-02-07 14:55
 * @version: v1.0.0
 */
@Data
public class ConverterDataListener extends AnalysisEventListener<ConverterData> {

    @Override
    public void invoke(ConverterData data, AnalysisContext context) {
        // 数据校验、数据转换等工作
        if (data != null) {
            if (data.getString().contains("5")) {
                // 不处理本条数据
//                return;
                // 直接结束数据处理，不再处理后面的数据 throw new RuntimeException()
                throw new RuntimeException("包含数字5");
            } else {
                // 处理数据
                System.out.println(data.toString());
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 处理完毕
        System.out.println("done");
    }
}
