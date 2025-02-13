package com.hungrystudio.adx.operator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.dto.AdxRequestData;
import com.hungrystudio.adx.util.CommonAnalysisUtils;
import com.hungrystudio.adx.dto.AdxClickData;
import com.hungrystudio.adx.serializer.DateSerializer;
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

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author slj
 * @date 2021/5/25
 */
public class S3ForRequestFlatMap extends RichFlatMapFunction<String, AdxRequestData> implements CheckpointedFunction {


    private static final Logger LOG = LoggerFactory.getLogger(S3ForRequestFlatMap.class);


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


    public S3ForRequestFlatMap(JSONObject json) {
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
    public void flatMap(String valueByte, Collector<AdxRequestData> out) {
        this.clickAllNum.add(1);
        JSONObject analysis = CommonAnalysisUtils.analysisToJSON(valueByte);
        try {
            this.outPutNum.add(1);
            getJson(analysis);
        } catch (Exception e) {
            this.errorNum.add(1);
            LOG.info("错误日志数据为" + analysis + "报错异常" + e.getMessage());
        }
        AdxRequestData adData = JsonUtils.fromJson(analysis.toJSONString(), AdxRequestData.class);
        out.collect(adData);
    }

    public void getJson(JSONObject analysis) {
        this.parseFromNum.add(1);
        String requestId = analysis.getString("rid");
        Long serverTime = analysis.getLongValue("timestamp");
        String os = analysis.getString("os");
        String bundle_id = analysis.getString("bundle_id");
        String str = Long.toString(serverTime);
        int length = str.length();
        if (length != 13) {
            serverTime = serverTime * 1000;
        }
        JSONArray bid_info = analysis.getJSONArray("bid_info");
        JSONObject win_ad = analysis.getJSONObject("win_ad");
        List<String> bidInfoList = IntStream.range(0, bid_info.size())  // 创建一个从0到bid_info长度的流
                .mapToObj(bid_info::getString)   // 将每个索引映射为 JSONArray 中的 String 元素
                .collect(Collectors.toList());   // 收集到 List 中
        if (requestId == null || os == null || bundle_id == null) {
            return;
        }
        int group_id = Math.abs(requestId.hashCode() % 10) + 1;
        this.requestNotNull.add(1);
        analysis.put("bid_info", bidInfoList);
        analysis.put("win_ad", win_ad !=null ? win_ad.toJSONString() : null);
        //分区字段
        analysis.put("bundle_id", bundle_id);
        analysis.put("os", os);
        analysis.put("hour", DateSerializer.getHH(serverTime));
        analysis.put("dt", DateSerializer.getyyyyMMdd(serverTime));
        analysis.put("is_request", 1);
        analysis.put("group_id", group_id);
        this.joinNotNull.add(1);
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
