package com.itplh.demo2.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.itplh.demo2.easyexcel.read.ReaderData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析监听器，
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 * @description:
 * @author: tanpeng
 * @date: 2020-02-07 13:00
 * @version: v1.0.0
 */
@Data
public class ReaderDataListener extends AnalysisEventListener<ReaderData> {

    private List<ReaderData> list = new ArrayList<>();

    @Override
    public void invoke(ReaderData data, AnalysisContext context) {
        if (data != null) {
            list.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
