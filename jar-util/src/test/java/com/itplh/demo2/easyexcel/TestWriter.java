package com.itplh.demo2.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.itplh.demo2.easyexcel.write.WriteData;
import com.itplh.util.TestFileUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 最简单的写 使用定义的对象写
     * <p>1. 创建excel对应的实体对象 参照{@link WriteData}
     * <p>2. 获取写入的数据 List<Class>
     * <p>3. 直接写即可
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
     * 使用list写
     * <p>1. 定义List<List<String>>结构的表头
     * <p>2. 定义List<List<Object>>结构的数据
     * <p>3. 写数据
     */
    @Test
    public void listWrite() {
        List<List<String>> head = new ArrayList<>();
        head.add(Arrays.asList("head1"));
        head.add(Arrays.asList("head2"));
        head.add(Arrays.asList("head3"));
        head.add(Arrays.asList("head4"));

        List<List<Object>> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(Arrays.asList("字符串" + i, new Date(), 0.56, (i % 2 == 0)));
        }

        EasyExcel.write(TestFileUtil.createNewFile(FILE_NAME_XLSX))
                .excelType(ExcelTypeEnum.XLSX)
                .head(head)
                .sheet("demoData")
             .doWrite(data);
    }

    /**
     * @description: 往excel中写多个sheet
     * 1、构建{@link ExcelWriter}
     * 2、构建{@link WriteSheet}
     * 3、往sheet写入数据
     * 4、调用{@link ExcelWriter#finish()}，否则数据不会写到文件中
     * @author: tanpeng
     * @date : 2020-02-14 11:22
     * @version: v1.0.0
     */
    @Test
    public void writeManySheet() {
        // 1、构建ExcelWriter对象
        ExcelWriter excelWriter = EasyExcel.write(TestFileUtil.createNewFile(FILE_NAME_XLSX), WriteData.class)
                .excelType(ExcelTypeEnum.XLSX)
                .build();

        // 2、构建WriteSheet对象
        WriteSheet sheet1 = EasyExcel.writerSheet(1, "sheet1").build();
        WriteSheet sheet2 = EasyExcel.writerSheet(2, "sheet2").build();
        WriteSheet sheet3 = EasyExcel.writerSheet(3, "sheet3").build();

        // 3、往sheet写入数据
        excelWriter.write(data(), sheet1);
        excelWriter.write(data(), sheet2);
        excelWriter.write(data(), sheet3);

        // 4、调用finish，否则数据不会写到文件中
        // ps: 即使构建ExcelWriter时设置了autoCloseStream(true) 这里也必须要调用finish
        excelWriter.finish();
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
