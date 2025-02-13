package com.hungrystudio.adx.test;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class UTCtoTimestamp {
    public static void main(String[] args) {
        String utcTimeStr = "2025-01-15";

        // 将日期字符串转换为 LocalDate
        LocalDate localDate = LocalDate.parse(utcTimeStr);

        // 转换为时间戳（秒级）
        long utcTimestamp = localDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        System.out.println(utcTimestamp);
    }
}

