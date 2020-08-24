package com.itplh.demo1.sizeof;

import com.carrotsearch.sizeof.RamUsageEstimator;
import com.itplh.util.TestFileUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @description:
 * @author: tanpenggood
 * @date: 2020-02-06 12:43
 * @version: v1.0.0
 */
public class TestObjectMemorySize {

    /**
     * @description: 测试15W initialCapacity为6的Map，每个Map三条数据的所占内存的大小
     * 2020-02-06 12:47:08 object size: 29440616
     * @author: tanpenggood
     * @date : 2020-02-06 12:50
     * @version: v1.0.0
     */
    @Test
    public void test15wFixLengthMapMemorySize() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 150000; i++) {
            list.add(new HashMap<String, String>(6) {{
                put("key1 ", "v1 ");
                put("key2 ", "v2 ");
                put("key3 ", "v3 ");
            }});
        }
        recordSizeOf(list);
    }

    /**
     * @description: 测试15W initialCapacity为默认值的Map，每个Map三条数据的所占内存的大小
     * 2020-02-06 12:47:08 object size: 34240616
     * @author: tanpenggood
     * @date : 2020-02-06 12:50
     * @version: v1.0.0
     */
    @Test
    public void test15wDefaultMapMemorySize() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 150000; i++) {
            list.add(new HashMap<String, String>() {{
                put("key1 ", "v1 ");
                put("key2 ", "v2 ");
                put("key3 ", "v3 ");
            }});
        }
        recordSizeOf(list);
    }

    /**
     * @description: 记录对象大小
     * @author: tanpenggood
     * @date : 2020/2/5 15:35
     * @version: v1.0.0
     */
    public static void recordSizeOf(Object o) {
        try (
                // 缓冲区默认8092kb
                BufferedWriter bw = new BufferedWriter(new FileWriter(TestFileUtil.getPath() + "sizeof.txt", true))
        ) {
            String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String str = nowTime + " object size: " + RamUsageEstimator.sizeOf(o) + "byte";
            bw.append(str);
            bw.newLine();
            bw.flush();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
