package com.hungrystudio.adx.serializer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author slj
 * @date 2021/6/7
 */
public class DateSerializer {


    /**
     * 线程安全的日期格式对象
     */
    private static final ThreadLocal <DateFormat> DATE_FORMAT = new ThreadLocal <DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            // 完整日期
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };

    /**
     * 线程安全的日期格式对象
     */
    private static final ThreadLocal <DateFormat> YMD = new ThreadLocal <DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            // 年月日
            return new SimpleDateFormat("yyyy-MM-dd");
        }

    };


    /**
     * 线程安全的日期格式对象
     */
    private static final ThreadLocal <DateFormat> YMDHS = new ThreadLocal <DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            // 年月日
            return new SimpleDateFormat("yyyyMMdd HH:ss");
        }

    };

    /**
     * 格式化完整日期
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.get().format(date);
    }

    /**
     * 格式化年月日
     *
     * @param date
     * @return yyyy-MM-dd格式的字符串
     */
    public static String formatYMD(Date date) {
        return YMD.get().format(date);
    }


    /**
     * 格式化年月日
     *
     * @param date
     * @return yyyy-MM-dd格式的字符串
     */
    public static String formatYMDHS(Date date) {
        return YMDHS.get().format(date);
    }

    /**
     * 获取指定日期0点的字符串
     *
     * @param date
     * @return 返回示例:2017-12-23 00:00:00
     */
    public static String getZeroPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期末点的字符串
     *
     * @param date
     * @return 返回示例:2017-12-23 23:59:59
     */
    public static String getLastPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期的0点的毫秒数
     *
     * @param date
     * @return
     */
    public static long getZeroPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取指定日期的末点的毫秒数
     *
     * @param date
     * @return
     */
    public static long getLastPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime();
    }


    public static String getHH(long timestamp) {
        Date now = new Date(timestamp);
        SimpleDateFormat sd = new SimpleDateFormat("HH");
        return sd.format(now);

    }

    public static String getyyyyMMdd(long timestamp) {
        Date now = new Date(timestamp);
        return formatYMD(now);
    }

    public static String getyyyyMMddHHmmss(long timestamp) {
        Date now = new Date(timestamp);
        return formatYMDHS(now);
    }


    public static Long stringToLong(String timestamp) {
        if (timestamp != null) {
            try {
                return new SimpleDateFormat("yyyyMMdd").parse(timestamp).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        Date now = new Date(1734514718L);
        long timestamp = 1734514720698L;
        String str = Long.toString(timestamp);
        int length = str.length();
        System.out.println(length);
        System.out.println(getyyyyMMdd(1734514720698L));
    }
}
