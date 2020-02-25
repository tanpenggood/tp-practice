package com.itplh.demo3.poi;

import com.itplh.util.TestFileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-17 15:16
 * @version: v1.0.0
 */
public class TestWriter {

    public static final String FILE_NAME_XLSX = "simpleWrite.xlsx";
    public static final String FILE_NAME_XLS = "simpleWrite.xls";

    /**
     * @description: 使用poi生成excel
     * 1、创建{@link Workbook}对象
     *      xlsx SXSSFWorkbook
     *      xls  HSSFWorkbook
     * 2、构建Sheet
     * 3、构建head及数据
     * 4、生成文件
     * @author: tanpeng
     * @date : 2020-02-17 15:59
     * @version: v1.0.0
     */
    @Test
    public void simpleWrite() {
        String fileName = FILE_NAME_XLS;
        // 创建Workbook
        Workbook workbook = fileName.endsWith(".xlsx") ?
                new SXSSFWorkbook() : new HSSFWorkbook();
        // 构建Sheet
        Sheet sheet = workbook.createSheet();
        // 构建head
        List<String> head = Arrays.asList("head1", "head2", "head3", "head4");
        Row headRow = sheet.createRow(0);
        for (int i = 0; i < head.size(); i++) {
            Cell cell = headRow.createCell(i);
            cell.setCellValue(head.get(i));
        }
        // 构建数据
        List<List<Object>> allData = new ArrayList<>();
        int totalRowNum = 10;
        for (int rowNum = 1; rowNum <= totalRowNum; rowNum++) {
            allData.add(Arrays.asList("字符串" + rowNum, new Date(), 0.56, (rowNum % 2 == 0)));
            Row row = sheet.createRow(rowNum);
            List<Object> oneRowData = allData.get(allData.size() - 1);
            for (int cellNum = 0; cellNum < oneRowData.size(); cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(oneRowData.get(cellNum).toString());
            }
        }
        // 生成文件
        try (
                OutputStream out = new FileOutputStream(TestFileUtil.createNewFile(fileName));
        ) {
            workbook.write(out);
            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
