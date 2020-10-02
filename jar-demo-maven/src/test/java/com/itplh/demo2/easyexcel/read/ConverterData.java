package com.itplh.demo2.easyexcel.read;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.itplh.demo2.easyexcel.converter.CustomDateTimeConverter;
import com.itplh.demo2.easyexcel.converter.CustomDisabledConverter;
import com.itplh.demo2.easyexcel.converter.CustomTitleConverter;
import lombok.Data;

import java.util.Date;

/**
 * @description: 基础数据类.这里的排序和excel里面的排序一致
 * @author: tanpenggood
 * @date: 2020-02-07 14:53
 * @version: v1.0.0
 */
@Data
public class ConverterData {
    /**
     * 自定义转换器，截取调“自定义：”
     */
    @ExcelProperty(converter = CustomTitleConverter.class)
    private String string;
    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @ExcelProperty(converter = CustomDateTimeConverter.class)
    private Date date;
    /**
     * 我想接收百分比的数字
     */
    @NumberFormat("#.##%")
    private String doubleData;
    /**
     * 自定义转换器，转换状态
     */
    @ExcelProperty(converter = CustomDisabledConverter.class)
    private Boolean disabled;
}
