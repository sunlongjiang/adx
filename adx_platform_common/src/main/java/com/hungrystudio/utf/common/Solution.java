package com.hungrystudio.utf.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static String topKFrequent(String[] words) {
        // 1.初始化 哈希表 key -> 字符串 value -> 出现的次数。
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        // 2.用 list 存储字符 key 然后自定义 Comparator 比较器对 value 进行排序。
        List<String> candidates = new ArrayList<>(count.keySet());
        // 此处为使用 lambda 写法
        candidates.sort((a, b) -> {
            // 字符串频率相等按照字典序比较使得大的在堆顶,Java 可以直接使用 compareTo 方法即可。
            if (count.get(a).equals(count.get(b))) {
                return a.compareTo(b);
            } else {
                // 字符串频率不等则按照频率排列。
                return count.get(b) - count.get(a);
            }
        });
        // 3.截取前 K 大个高频单词返回结果。
        return candidates.get(0);
    }

    public static void main(String[] args) {
        String word1 = ",,";
        String[] split = word1.split(",");
        System.out.println(split.length);
        System.out.println(split);
        String strings = Solution.topKFrequent(split);
        System.out.println(strings);

    }
}
