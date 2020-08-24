package com.itplh.demo3.poi;

import com.itplh.demo1.sizeof.TestObjectMemorySize;
import com.itplh.util.TestFileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @description:
 * @author: tanpenggood
 * @date: 2020-02-17 11:23
 * @version: v1.0.0
 */
public class TestReader {

    public static final String FILE_NAME_XLSX = "simpleWrite.xlsx";
    public static final String FILE_NAME_XLS = "simpleWrite.xls";

    /**
     * @description: 使用poi读取excel
     * 1、获取输入流
     * 2、获取{@link Workbook}对象
     * 3、解析Workbook对象，提取数据
     * @author: tanpenggood
     * @date : 2020-02-17 12:52
     * @version: v1.0.0
     */
    @Test
    public void simpleReader() throws IOException {
        long start = System.currentTimeMillis();
        String fileName = FILE_NAME_XLSX;
        // 获取文件类型
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 获取输入流
        InputStream inputStream = TestFileUtil.getResourcesFileInputStream(fileName);
        // 获取Excel工作簿
        Workbook workbook = null;
        if ("xls".equalsIgnoreCase(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if ("xlsx".equalsIgnoreCase(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        // used memory size
        TestObjectMemorySize.recordSizeOf(workbook);
        // file size
        System.out.println("file size\t" + TestFileUtil.readFile(fileName).length() + "byte");
        // used time
        System.out.println("use time\t" + (System.currentTimeMillis() - start) +"ms");
        // 读取数据
        parseExcel(workbook);
    }

    /**
     * @description: 解析Workbook对象，获取数据
     * @author: tanpenggood
     * @date : 2020-02-17 12:10
     * @version: v1.0.0
     */
    private void parseExcel(Workbook workbook) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                Arrays.asList(0,1,2,3).stream().forEach(index ->
                        System.out.print(convertCellValueToString(row.getCell(index)) + "\t")
                );
                System.out.println();
            }
        }
    }

    /**
     * @description: 将单元格内容转换为字符串
     * @author: tanpenggood
     * @date : 2020-02-17 12:10
     * @version: v1.0.0
     */
    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();
                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }
}
