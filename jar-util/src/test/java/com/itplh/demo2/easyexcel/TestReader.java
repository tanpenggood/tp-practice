package com.itplh.demo2.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.DefaultConverterLoader;
import com.itplh.demo2.easyexcel.converter.CustomDateTimeConverter;
import com.itplh.demo2.easyexcel.converter.CustomDisabledConverter;
import com.itplh.demo2.easyexcel.converter.CustomTitleConverter;
import com.itplh.demo2.easyexcel.listener.ConverterDataListener;
import com.itplh.demo2.easyexcel.listener.ObjectListener;
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
        ConverterDataListener converterDataListener = new ConverterDataListener();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(TestFileUtil.getPath() + FILE_NAME_XLSX, ConverterData.class, converterDataListener)
                // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
                // 如果就想单个字段使用请使用@ExcelProperty 指定converter
                // .registerConverter(new CustomStringStringConverter())
                // 读取sheet
                .sheet()
                .doRead();
        System.out.println(converterDataListener.getList().stream().count());
        converterDataListener.getList().stream().forEach(System.out::println);
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

}
