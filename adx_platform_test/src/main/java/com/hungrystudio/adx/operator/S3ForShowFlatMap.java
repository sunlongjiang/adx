package com.hungrystudio.adx.operator;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.dto.AdxShowData;
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


/**
 * @author slj
 * @date 2021/5/25
 */
public class S3ForShowFlatMap extends RichFlatMapFunction <String, AdxShowData> implements CheckpointedFunction {


    private static final Logger LOG = LoggerFactory.getLogger(S3ForShowFlatMap.class);


    private Class <?> pbClass;
    private transient Method parseFunc;

    private ListState <Long> in_stateforclick;
    private Long tmp = 0L;

    private final LongCounter clickAllNum = new LongCounter();

    private final LongCounter errorNum = new LongCounter();

    private final LongCounter requestNotNull = new LongCounter();

    private final LongCounter joinNotNull = new LongCounter();

    private final LongCounter outPutNum = new LongCounter();


    private final LongCounter beylaidNotNull = new LongCounter();


    private final LongCounter activeDaysNum = new LongCounter();


    private final LongCounter parseFromNum = new LongCounter();

    private JSONObject json;




    public S3ForShowFlatMap(JSONObject json) {
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
    public void flatMap(String valueByte, Collector <AdxShowData> out) {
        this.clickAllNum.add(1);
        JSONObject analysis = CommonAnalysisUtils.analysisToJSON(valueByte);
        try {
            this.outPutNum.add(1);
            getJson(analysis);
        } catch (Exception e) {
            this.errorNum.add(1);
            LOG.info("错误日志数据为" + analysis + "报错异常" + e.getMessage());
        }
        AdxShowData adData = JsonUtils.fromJson(analysis.toJSONString(), AdxShowData.class);
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
        String str = Long.toString(serverTime);
        int length = str.length();
        if (length != 13) {
            serverTime = serverTime * 1000;
        }
        int group_id = Math.abs(requestId.hashCode() % 10) + 1;
        this.requestNotNull.add(1);

        analysis.put("bundle_id", bundle_id);
        analysis.put("os", os);
        analysis.put("hour", DateSerializer.getHH(serverTime));
        analysis.put("dt", DateSerializer.getyyyyMMdd(serverTime));
        analysis.put("is_show", 1);
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
        ListStateDescriptor <Long> descriptor =
                new ListStateDescriptor <>(
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
