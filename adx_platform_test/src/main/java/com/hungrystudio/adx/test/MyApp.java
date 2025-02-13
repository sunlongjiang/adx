package com.hungrystudio.adx.test;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.dto.AdxClickData;
import com.hungrystudio.adx.sink.MyReportBasePathBucketAssigner;
import com.hungrystudio.adx.util.JsonUtils;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.reflect.ReflectData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp {
    // 创建 Logger 实例
    private static final Logger logger = LoggerFactory.getLogger(MyApp.class);

    public static void main(String[] args) {


        String str = "{\"country\":\"IN\",\"cst\":\"1734578844\",\"pid\":\"1010\",\"ptype\":\"2\",\"rid\":\"342b7837-fab3-4304-a600-18c722fdcfa8\",\"network\":\"2\",\"dt\":\"2024-12-19\",\"adid\":\"7999e87a-24e0-1000-eec2-002a54b9fe34\",\"hour\":\"01\",\"adomain\":[\"advertiserDomain.sandbox.inmobi.com\"],\"vendor\":\"Apple\",\"idfv\":\"55B3B01A-6926-4878-B3E6-E2C2648ABE92\",\"model\":\"iPhone XS\",\"timestamp\":\"1734578895\",\"ver\":\"1.0\",\"os\":\"ios\",\"crid\":\"<adv>88b37efcd5f34d368e60317c706942a4<crid>2044a6d01da645efac6cc89feb86d44d\",\"dsp_name\":\"inmobi\",\"ip\":\"103.146.222.24\",\"carrier\":\"NOCARRIER\",\"ecpm\":\"99\",\"group_id\":2,\"bundle_id\":\"com.mathbrain.sudoku\",\"is_click\":1,\"ver_n\":\"10\",\"sdk_ver\":\"1.0.0\"}";
        System.out.println(new MyReportBasePathBucketAssigner("s3://hungry-studio-data-warehouse/user/sunlj/adx/data/dt=%s/hour=%s/").getBucketId(JSONObject.parseObject(str), null));

        AdxClickData adData = JsonUtils.fromJson(str, AdxClickData.class);
        System.out.println("Parsed object: " + adData);


        Class<?> type = AdxClickData.class;

        // 获取原始 schema
        Schema schema = ReflectData.get().getSchema(type);

        // 创建一个新的 schema builder，用于修改每个字段
        SchemaBuilder.FieldAssembler<Schema> fieldAssembler = SchemaBuilder.record("AdxClick")
                .namespace("com.example")
                .fields();

        // 遍历原始 schema 的字段并将所有字段都改为可空类型
        for (Schema.Field field : schema.getFields()) {
            String fieldName = field.name();
            Schema fieldType = field.schema();

            // 将每个字段的类型设置为 ["null", <原类型>]
            Schema nullableType = Schema.createUnion(Schema.create(Schema.Type.NULL), fieldType);

            // 将字段添加到新的 schema 中
            fieldAssembler.name(fieldName).type(nullableType).noDefault();
        }

        // 构建新的 schema
        Schema newSchema = fieldAssembler.endRecord();

        // 输出新的 schema 字符串
        System.out.println(newSchema.toString());



    }
}
