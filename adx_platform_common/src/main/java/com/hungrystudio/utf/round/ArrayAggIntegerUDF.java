package com.hungrystudio.utf.round;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 定义泛型 UDF 类，T 为 tuple._2 的数据类型
public class ArrayAggIntegerUDF implements UDF1<String, List<Integer>> {

    @Override
    public List<Integer> call(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        // 提取元素列表
        List<Integer> sortedElements = new ArrayList<>();

        try {
            List<Tuple2<Integer, Integer>> validTupleList = convertStringToListTuple(input);
            // 过滤无效的元组
            List<Tuple2<Integer, Integer>> validTuples = new ArrayList<>();
            for (Tuple2<Integer, Integer> tuple : validTupleList) {
                if (tuple != null && tuple._1() != null && tuple._2() != null) {
                    validTuples.add(tuple);
                }
            }
            // 按 index_id 排序
            validTuples.sort(Comparator.comparingInt(Tuple2::_1));
            for (Tuple2<Integer, Integer> tuple : validTuples) {
                sortedElements.add(tuple._2());
            }
        } catch (Exception e) {
            sortedElements.add(-1);
            sortedElements.add(-1);
            sortedElements.add(-1);
        }
        return sortedElements;
    }

    public static List<Tuple2<Integer, Integer>> convertStringToListTuple(String input) {
        List<Tuple2<Integer, Integer>> validTuples = new ArrayList<>();
        if (input == null || input.isEmpty()) {
            return validTuples;
        }
        String[] pairs = input.split(",");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                int first = Integer.parseInt(parts[0]);
                int second = Integer.parseInt(parts[1]);
                validTuples.add(new Tuple2<>(first, second));
            }
        }
        return validTuples;
    }

    public static void main(String[] args) {
        String input = "1:1,2:0,3:2";
        List<Tuple2<Integer, Integer>> validTuples = convertStringToListTuple(input);

        // 打印结果
        for (Tuple2<Integer, Integer> tuple : validTuples) {
            System.out.println("(" + tuple._1() + ", " + tuple._2() + ")");
        }
    }
}
