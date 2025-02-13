package com.hungrystudio.adx.serializer;

import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.serialization.BulkWriter;
import org.apache.flink.core.fs.FSDataOutputStream;

import java.io.IOException;

/**
 * Created by slj on 2021/9/10.
 */
public class DayBulkWriterFactory implements BulkWriter.Factory <JSONObject> {
    @Override
    public BulkWriter <JSONObject> create(FSDataOutputStream out) throws IOException {
        return new DayBulkWriter(out);
    }
}
