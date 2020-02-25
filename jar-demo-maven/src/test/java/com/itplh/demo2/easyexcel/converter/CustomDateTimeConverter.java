package com.itplh.demo2.easyexcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-07 14:19
 * @version: v1.0.0
 */
public class CustomDateTimeConverter implements Converter<Date> {
    @Override
    public Class supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Date convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 简化处理，直接new Date()
        return new Date();
    }

    @Override
    public CellData convertToExcelData(Date value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

}
