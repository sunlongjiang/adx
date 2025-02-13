package com.hungrystudio.adx.operator;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.dto.AdxGeoedgeData;
import com.hungrystudio.adx.serializer.DateSerializer;
import com.hungrystudio.adx.util.CommonAnalysisUtils;
import com.hungrystudio.adx.util.JsonUtils;
import org.apache.flink.api.common.accumulators.LongCounter;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.zip.GZIPInputStream;


/**
 * @author slj
 * @date 2021/5/25
 */
public class S3ForGeoedgeFlatMap extends RichFlatMapFunction<String, AdxGeoedgeData> implements CheckpointedFunction {


    private static final Logger LOG = LoggerFactory.getLogger(S3ForGeoedgeFlatMap.class);


    private Class<?> pbClass;
    private transient Method parseFunc;

    private ListState<Long> in_stateforclick;
    private Long tmp = 0L;

    private final LongCounter clickAllNum = new LongCounter();

    private final LongCounter errorNum = new LongCounter();

    private final LongCounter requestNotNull = new LongCounter();

    private final LongCounter joinNotNull = new LongCounter();

    private final LongCounter outPutNum = new LongCounter();


    private final LongCounter beylaidNotNull = new LongCounter();


    private final LongCounter activeDaysNum = new LongCounter();


    private final LongCounter parseFromNum = new LongCounter();

    private String host;
    private int port;
    private String segment;
    private JSONObject json;


    public S3ForGeoedgeFlatMap(JSONObject json, boolean flag) {
        this.json = json;
        this.host = json.getString("host");
        this.port = json.getInteger("port");
        this.segment = json.getString("segment");
    }


    public S3ForGeoedgeFlatMap(JSONObject json) {
        this.json = json;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        getRuntimeContext().addAccumulator("outPutNum", this.outPutNum);
        getRuntimeContext().addAccumulator("parseFromNum", this.parseFromNum);
        getRuntimeContext().addAccumulator("activeDaysNum", this.activeDaysNum);
        getRuntimeContext().addAccumulator("errorNum", this.errorNum);
        getRuntimeContext().addAccumulator("clickAllNum", this.clickAllNum);
        getRuntimeContext().addAccumulator("requestNotNull", this.requestNotNull);
        getRuntimeContext().addAccumulator("joinNotNull", this.joinNotNull);
        getRuntimeContext().addAccumulator("beylaidNotNull", this.beylaidNotNull);
    }

    @Override
    public void flatMap(String valueByte, Collector<AdxGeoedgeData> out) {
        this.clickAllNum.add(1);
        JSONObject analysis = CommonAnalysisUtils.analysisToJSON(valueByte);
        try {
            this.outPutNum.add(1);
            getJson(analysis);
        } catch (Exception e) {
            this.errorNum.add(1);
            LOG.info("错误日志数据为" + analysis + "报错异常" + e.getMessage());
        }
        AdxGeoedgeData adData = JsonUtils.fromJson(analysis.toJSONString(), AdxGeoedgeData.class);
        out.collect(adData);
    }

    public void getJson(JSONObject analysis) {
        this.parseFromNum.add(1);
        String requestId = analysis.getString("rid");
        long serverTime = analysis.getLongValue("timestamp");
        String os = analysis.getString("os");
        String bundle_id = analysis.getString("bundle_id");
        if (requestId == null || os == null || bundle_id == null) {
            return;
        }
        byte[] adm = analysis.getBytes("adm");
        String str = Long.toString(serverTime);
        int length = str.length();
        if (length != 13) {
            serverTime = serverTime * 1000;
        }
        this.requestNotNull.add(1);

        analysis.put("bundle_id", bundle_id);
        analysis.put("adm", adm != null ? exactGzip(adm) : null);
        analysis.put("os", os);
        analysis.put("hour", DateSerializer.getHH(serverTime));
        analysis.put("dt", DateSerializer.getyyyyMMdd(serverTime));
        this.joinNotNull.add(1);
    }

    public static String exactGzip(byte[] decodedBytes) {
        // 使用GZIP解压
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedBytes);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            // 使用缓冲区读取解压数据
            byte[] buffer = new byte[1024];
            int length;
            while ((length = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }

            // 获取解压后的字节数据
            byte[] decompressedData = byteArrayOutputStream.toByteArray();

            return new String(decompressedData);

        } catch (IOException e) {
            LOG.info("adm解压失败" + decodedBytes + "报错异常" + e.getMessage());
        }
        return null;
    }


    @Override
    public void snapshotState(FunctionSnapshotContext context) throws Exception {
        in_stateforclick.clear();
        in_stateforclick.add(tmp);
    }

    @Override
    public void initializeState(FunctionInitializationContext context) throws Exception {
        ListStateDescriptor<Long> descriptor =
                new ListStateDescriptor<>(
                        "in-state",
                        Long.class);

        in_stateforclick = context.getOperatorStateStore().getListState(descriptor);

        if (context.isRestored()) {
            for (Long element : in_stateforclick.get()) {
                tmp = element;
            }
        }
    }
}
