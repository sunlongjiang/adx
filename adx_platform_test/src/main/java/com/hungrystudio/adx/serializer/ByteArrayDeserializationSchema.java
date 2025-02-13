package com.hungrystudio.adx.serializer;

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

/**
 * Created by slj on 2021/6/3.
 */
public class ByteArrayDeserializationSchema extends AbstractDeserializationSchema <byte[]> {


    @Override
    public byte[] deserialize(byte[] message) {
        return message;
    }

}
