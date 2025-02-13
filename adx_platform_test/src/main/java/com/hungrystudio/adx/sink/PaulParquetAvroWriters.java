package com.hungrystudio.adx.sink;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.flink.formats.parquet.ParquetBuilder;
import org.apache.flink.formats.parquet.ParquetWriterFactory;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.io.OutputFile;

import java.io.IOException;

public class PaulParquetAvroWriters {

    public static <T extends SpecificRecordBase> ParquetWriterFactory<T> forSpecificRecord(Class<T> type, CompressionCodecName compressionCodecName) {
        final String schemaString = SpecificData.get().getSchema(type).toString();
        final ParquetBuilder<T> builder = (out) -> createAvroParquetWriter(schemaString, SpecificData.get(), out,compressionCodecName);
        return new ParquetWriterFactory<>(builder);
    }
    //compressionCodecName 压缩算法
    public static ParquetWriterFactory<GenericRecord> forGenericRecord(Schema schema, CompressionCodecName compressionCodecName) {
        final String schemaString = schema.toString();
        final ParquetBuilder<GenericRecord> builder = (out) -> createAvroParquetWriter(schemaString, GenericData.get(), out,compressionCodecName);
        return new ParquetWriterFactory<>(builder);
    }
    //compressionCodecName 压缩算法
    public static <T> ParquetWriterFactory<T> forReflectRecord(Class<T> type,CompressionCodecName compressionCodecName) {
        Schema schema = ReflectData.get().getSchema(type);
        // 创建一个新的 schema builder，用于修改每个字段
        SchemaBuilder.FieldAssembler<Schema> fieldAssembler = SchemaBuilder.record("Adx")
                .namespace("com.hungrystudio.adx")
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
            String schemaString = fieldAssembler.endRecord().toString();
        final ParquetBuilder<T> builder = (out) -> createAvroParquetWriter(schemaString, ReflectData.get(), out,compressionCodecName);
        return new ParquetWriterFactory<>(builder);
    }
    //compressionCodecName 压缩算法
    private static <T> ParquetWriter<T> createAvroParquetWriter(
            String schemaString,
            GenericData dataModel,
            OutputFile out,
            CompressionCodecName compressionCodecName) throws IOException {
        final Schema schema = new Schema.Parser().parse(schemaString);
        return AvroParquetWriter.<T>builder(out)
                .withSchema(schema)
                .withDataModel(dataModel)
                .withCompressionCodec(compressionCodecName)//压缩算法
                .build();
    }
    private PaulParquetAvroWriters() {}
}
