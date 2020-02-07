package com.itplh.demo2.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.itplh.demo2.easyexcel.write.WriteData;
import com.itplh.util.TestFileUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-07 14:43
 * @version: v1.0.0
 */
public class TestWriter {


    public static final String FILE_NAME_XLSX = "simpleWrite.xlsx";
    public static final String FILE_NAME_XLS = "simpleWrite.xls";

    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link WriteData}
     * <p>2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(TestFileUtil.createNewFile(FILE_NAME_XLSX), WriteData.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("demoData")
                .doWrite(data());
    }

    /**
     * @description: 生成模拟数据
     * @author: tanpeng
     * @date : 2020-02-07 14:40
     * @version: v1.0.0
     */
    private List<WriteData> data() {
        List<WriteData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WriteData data = WriteData.builder()
                    .string("字符串" + i)
                    .date(new Date())
                    .doubleData(0.56)
                    .disabled((i % 2 == 0))
                    .build();
            list.add(data);
        }
        return list;
    }

}
