package com.hungrystudio.utf.common;

import org.apache.spark.sql.api.java.UDF1;


// 实现 UDF1 接口，UDF1 表示该 UDF 接收一个输入参数
public class MosCommonElement implements UDF1<String, String> {
    // 重写 call 方法，定义 UDF 的具体逻辑
    @Override
    public String call(String input) throws Exception {
        if (input == null) {
            return null;
        }
        String[] split = input.split(",");
        if (split.length == 0) {
            return null;
        }
        try {
            return Solution.topKFrequent(split);
        } catch (Exception e) {
            return "异常数据";
        }
    }
}