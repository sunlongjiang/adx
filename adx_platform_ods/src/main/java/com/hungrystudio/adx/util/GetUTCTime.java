package com.hungrystudio.adx.util;


import com.hungrystudio.adx.serializer.DateSerializer;
import org.apache.flink.api.common.functions.RuntimeContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GetUTCTime {


    private static ThreadLocal <DateFormat> threadFormatertime = new ThreadLocal <DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };


    private static ThreadLocal <DateFormat> threadFormaterhour = new ThreadLocal <DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH");
        }
    };

    // 取得本地时间：
    private static Calendar cal = Calendar.getInstance();
    // 取得时间偏移量：
    private static int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
    // 取得夏令时差：
    private static int dstOffset = cal.get(Calendar.DST_OFFSET);

    public static long getUTCTimeStr(long millis) {

        cal.setTimeInMillis(millis);
        // 从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        long mills = cal.getTimeInMillis();
        return mills;
    }

    public static String setUTCTime(long millis) {

        cal.setTimeInMillis(millis);

        DateFormat dateFormat = threadFormatertime.get();
        return dateFormat.format(cal.getTime());
    }


    public static String setUTChour(long millis) {
        cal.setTimeInMillis(millis);
        DateFormat foo = threadFormaterhour.get();
        return foo.format(cal.getTime());
    }

    public void getGMTTime() {
        //mothed 2
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(gmtTime);
        System.out.println("GMT Time: " + format.format(date));

        //method 2
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeZone(gmtTime);
        //System.out.println(calendar1.getTime());    //时区无效
        //System.out.println(calendar1.getTimeInMillis()); //时区无效
        System.out.println("GMT hour = " + calendar1.get(Calendar.HOUR));
    }


    public static void main(String[] args) {

        Date date = new Date();
        long utcTimeStr = GetUTCTime.getUTCTimeStr(date.getTime());

        System.out.println(DateSerializer.getHH(1645825325L*1000));
        System.out.println(DateSerializer.getyyyyMMdd(1645825325L*1000));

        String utcTime = GetUTCTime.setUTCTime(1645825325L*1000);
        String utcHour = GetUTCTime.setUTChour(1645825325L*1000);

        System.out.println(utcTime);
        System.out.println(utcHour);


    }
}
