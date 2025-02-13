package com.hungrystudio.adx.sink;

import org.apache.flink.streaming.api.functions.sink.filesystem.PartFileInfo;

import java.io.IOException;

public class MyPartFileInfo <BucketID> implements PartFileInfo<BucketID> {
    @Override
    public BucketID getBucketId() {
        return null;
    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public long getSize() throws IOException {
        return 0;
    }

    @Override
    public long getLastUpdateTime() {
        return 0;
    }
}
