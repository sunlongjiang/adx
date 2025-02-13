package com.hungrystudio.adx.serializer;

import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.serialization.BulkWriter;
import org.apache.flink.core.fs.FSDataOutputStream;
import org.apache.flink.util.Preconditions;

import java.io.IOException;

/**
 * Created by slj on 2021/9/10.
 */
public class DayBulkWriter implements BulkWriter <JSONObject> {



    FSDataOutputStream stream = null;


    public DayBulkWriter(FSDataOutputStream stream) {
        this.stream  = Preconditions.checkNotNull(stream);;
    }

    @Override
    public void addElement(JSONObject element) throws IOException {

        this.stream.write(element.toJSONString().getBytes());
    }

    @Override
    public void flush() throws IOException {


    }

    @Override
    public void finish() throws IOException {
        this.flush();

    }
}
