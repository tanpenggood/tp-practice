package com.itplh.demo2.easyexcel.write;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.itplh.demo2.easyexcel.converter.CustomDisabledConverter;
import com.itplh.demo2.easyexcel.converter.CustomTitleConverter;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-07 09:17
 * @version: v1.0.0
 */
@Data
@Builder
@HeadRowHeight(25)
@ContentRowHeight(20)
@ColumnWidth(30)
public class WriteData {
    @ExcelProperty(value = "字符串标题", converter = CustomTitleConverter.class)
    private String string;
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    @ExcelProperty(value = "日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelProperty(value = "禁用标志", converter = CustomDisabledConverter.class)
    private Boolean disabled;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
