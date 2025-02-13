package com.hungrystudio.utf.block;

import org.apache.spark.sql.api.java.UDF1;


import static com.hungrystudio.utf.block.Main.getAreaComplexValue;

// 实现 UDF1 接口，UDF1 表示该 UDF 接收一个输入参数
public class AreaComplexValue implements UDF1<String, Integer> {
    // 重写 call 方法，定义 UDF 的具体逻辑
    @Override
    public Integer call(String input) {
        if (input == null || input.isEmpty()) {
            return -1;
        }
        try {
            return getAreaComplexValue(input);
        } catch (Exception e) {
            return -100;
        }
    }

    public static void main(String[] args) {
        AreaComplexValue areaComplexValue = new AreaComplexValue();
        System.out.println(
                areaComplexValue.call("")
        );
    }
}