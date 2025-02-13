package com.hungrystudio.adx.test;


import com.hungrystudio.adx.dto.Person;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 *
 * @author hy
 * @createTime 2021-05-23 08:18:33
 * @description 当前内容主要为测试和使用当前的Flink，主要为本地方式运行这个Flink
 *
 */
public class LocalRunningDataSourceTest {
    public static void main(String[] args) {
        // 采用本地模式
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        // 设定数据来源为集合数据
        DataStream<Person> flintstones = env.fromElements(new Person("Fred", 35),
                        new Person("Wilma", 35),
                        new Person("Pebbles", 2));

        DataStream<Person> adults = flintstones.filter(new FilterFunction<Person>() {
            @Override
            public boolean filter(Person person) throws Exception {
                return person.getAge() >= 18;
            }
        });

        adults.print();



        // 设置数据来源为当前的文本文件：
        DataStreamSource<String> readTextFile = env.readTextFile("/Users/admin/Downloads/data/test/hungry-studio-adx/adx_platform_test/src/main/resources/adx.txt");
        // 直接读取文件为文本类型，最后进行print操作
        readTextFile.print();

        try {
            // 最后开始执行
            JobExecutionResult result = env.execute("Fraud Detection");
            if(result.isJobExecutionResult()) {
                System.out.println("执行完毕......");
            }
            System.out.println();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

