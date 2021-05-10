package com.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;

/**
 * 基础数据类
 *
 * @author Jiaju Zhuang
 **/
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;

    public static void main(String[] args) throws InterruptedException {
        int []a = {1,2,3,4,5,6};
        int[] ints = Arrays.copyOf(a, a.length + 1);
        System.out.println(Arrays.toString(ints));
    }
}