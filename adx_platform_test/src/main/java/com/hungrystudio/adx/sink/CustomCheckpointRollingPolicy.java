package com.hungrystudio.adx.sink;

import org.apache.flink.streaming.api.functions.sink.filesystem.PartFileInfo;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.CheckpointRollingPolicy;

import java.io.IOException;

public class CustomCheckpointRollingPolicy<IN, BucketID> extends CheckpointRollingPolicy<IN, BucketID> {

    private static final long maxSize = 100_000;  // 100KB

    @Override
    public boolean shouldRollOnEvent(PartFileInfo<BucketID> partFileState, IN element) throws IOException {
        return partFileState.getSize() >= maxSize;
    }
    @Override
    public boolean shouldRollOnProcessingTime(PartFileInfo<BucketID> partFileState, long currentTime) throws IOException {
        return false;
    }
}
