package com.itplh.demo2.easyexcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @description:
 * @author: tanpenggood
 * @date: 2020-02-07 10:44
 * @version: v1.0.0
 */
public class CustomDisabledConverter implements Converter<Boolean> {

    @Override
    public Class supportJavaTypeKey() {
        return Boolean.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Boolean convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return "可用".equals(cellData.getStringValue());
    }

    @Override
    public CellData convertToExcelData(Boolean value, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value ? "可用" : "禁用");
    }

}
