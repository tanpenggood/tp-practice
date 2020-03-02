package com.itplh.demo2.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.converters.DefaultConverterLoader;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.itplh.demo2.easyexcel.converter.CustomDateTimeConverter;
import com.itplh.demo2.easyexcel.converter.CustomDisabledConverter;
import com.itplh.demo2.easyexcel.converter.CustomTitleConverter;
import com.itplh.demo2.easyexcel.listener.ConverterDataListener;
import com.itplh.demo2.easyexcel.listener.ObjectListener;
import com.itplh.demo2.easyexcel.listener.PerformanceListener;
import com.itplh.demo2.easyexcel.listener.ReaderDataListener;
import com.itplh.demo2.easyexcel.read.ConverterData;
import com.itplh.demo2.easyexcel.read.ReaderData;
import com.itplh.util.TestFileUtil;
import org.junit.Test;

import java.util.List;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-07 14:43
 * @version: v1.0.0
 */
public class TestReader {

    public static final String FILE_NAME_XLSX = "simpleWrite.xlsx";
    public static final String FILE_NAME_XLS = "simpleWrite.xls";

    /**
     * 异步读
     * <p>
     * 1. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ObjectListener}
     * <p>
     * 2. 直接读即可
     */
    @Test
    public void asyncReader() {
        ObjectListener listener = new ObjectListener();
        // 这里 需要指定监听器用于存储数据，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(TestFileUtil.readFile(FILE_NAME_XLSX), listener)
                .sheet()
                // 同步读 doReadSync()
                // 异步读 doRead()
                .doRead();
        System.out.println(listener.getList().stream().count());
        listener.getList().stream().forEach(System.out::println);
    }

    /**
     * @description: 同步读
     * @author: tanpeng
     * @date : 2020-02-07 15:57
     * @version: v1.0.0
     */
    @Test
    public void syncReader() {
        List<Object> list = EasyExcel.read(TestFileUtil.readFile(FILE_NAME_XLSX))
                .sheet()
                .doReadSync();
        System.out.println(list.stream().count());
        list.stream().forEach(System.out::println);
    }

    /**
     * @description: 读excel中的多个sheet
     * 1、构建{@link ExcelReader}
     * 2、构建{@link ReadSheet}
     * 3、读取sheet的数据
     * 4、调用{@link ExcelReader#finish()}，读的时候会创建临时文件，到时磁盘会崩的
     * @author: tanpeng
     * @date : 2020-02-14 12:06
     * @version: v1.0.0
     */
    @Test
    public void readManySheet() {
        SyncReadListener listener = new SyncReadListener();
        ExcelReader excelReader = EasyExcel.read(TestFileUtil.readFile(FILE_NAME_XLSX), listener).build();

        ReadSheet sheet1 = EasyExcel.readSheet(0).build();
        ReadSheet sheet2 = EasyExcel.readSheet(1).build();
        ReadSheet sheet3 = EasyExcel.readSheet(2).build();

        // excelReader.read(sheet1);excelReader.read(sheet1); 重复读，会出现如下异常
        // com.alibaba.excel.exception.ExcelAnalysisException: Cannot read sheet repeatedly.

        // excelReader.read(sheet1).finish();excelReader.read(sheet2); 未读完提前关闭流，会出现如下异常
        // com.alibaba.excel.exception.ExcelAnalysisException: java.io.IOException: Stream closed

        excelReader.read(sheet1, sheet2, sheet3);

        System.out.println(listener.getList().size());
        listener.getList().stream().forEach(System.out::println);

        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    /**
     * @description: 读取所有sheet的数据
     * @author: tanpeng
     * @date : 2020-02-13 10:50
     * @version: v1.0.0
     */
    @Test
    public void doReadAll() {
        ObjectListener listener = new ObjectListener();
        // 这里 需要指定监听器用于存储数据，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(TestFileUtil.readFile(FILE_NAME_XLSX), listener)
                .doReadAll();
        System.out.println(listener.getList().stream().count());
        listener.getList().stream().forEach(System.out::println);
    }

    /**
     * 日期、数字或者自定义格式转
     * 返回指定对象 {@link ConverterData}
     * <p>
     * 默认读的转换器{@link DefaultConverterLoader#loadDefaultReadConverter()}
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ConverterData}.里面可以使用注解{@link DateTimeFormat}、{@link NumberFormat}或者自定义注解
     * <p>
     * 2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ConverterDataListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void converterRead() {
        try {
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet
            EasyExcel.read(TestFileUtil.getPath() + FILE_NAME_XLSX, ConverterData.class, new ConverterDataListener())
                    // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
                    // 如果就想单个字段使用请使用@ExcelProperty 指定converter
                    // .registerConverter(new CustomStringStringConverter())
                    // 读取sheet
                    .sheet()
                    .doRead();
        } catch (ExcelAnalysisException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @description: 指定自定义转换器读
     * 返回指定对象 {@link ReaderData}
     * @author: tanpeng
     * @date : 2020-02-07 15:11
     * @version: v1.0.0
     */
    @Test
    public void registerConverterReader() {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        ReaderDataListener readerDataListener = new ReaderDataListener();
        EasyExcel.read(TestFileUtil.readFile(FILE_NAME_XLSX), ReaderData.class, readerDataListener)
                .registerConverter(new CustomTitleConverter())
                .registerConverter(new CustomDateTimeConverter())
                .registerConverter(new CustomDisabledConverter())
                .sheet()
                .doRead();
        System.out.println(readerDataListener.getList().stream().count());
        readerDataListener.getList().stream().forEach(System.out::println);
    }

    /**
     * @description: 读取31M(46W行25列)的Excel，性能测试
     * -Xms64m  -Xmx64m  70560ms
     * -Xms128m -Xmx128m 65475ms
     * -Xms512m -Xmx512m 65364ms
     * @author: tanpeng
     * @date : 2020-03-02 21:35
     * @version: v1.0.0
     */
    @Test
    public void readPerformance() {
        EasyExcel.read(TestFileUtil.readFile(FILE_NAME_XLSX), new PerformanceListener())
                .sheet()
                .doRead();
    }

}
