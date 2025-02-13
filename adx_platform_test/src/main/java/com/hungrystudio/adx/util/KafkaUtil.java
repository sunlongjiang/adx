package com.hungrystudio.adx.util;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.hungrystudio.adx.util.CommonAnalysisUtils.analysisToString;

public class KafkaUtil {

    public static KafkaSource<String> getkafkaSource(String kafkaType, String kafkaBrokers, String groupId, String topic, String logType, boolean isEarliest, Long timestamp) {

        OffsetsInitializer offsetsInitializer = null;
        if (timestamp != 0) {
            offsetsInitializer = OffsetsInitializer.timestamp(timestamp);
        } else {
            offsetsInitializer = isEarliest
                    ? OffsetsInitializer.earliest()
                    : OffsetsInitializer.committedOffsets(OffsetResetStrategy.LATEST);
        }
        if ("msk".equalsIgnoreCase(kafkaType)) {
            return KafkaSource
                    .<String>builder()
                    .setBootstrapServers(kafkaBrokers)  // kafka服务器
                    .setGroupId(groupId)  // 消费者组
                    .setTopics(topic)  // 消费者主题
                    .setStartingOffsets(offsetsInitializer)
                    .setValueOnlyDeserializer(new DeserializationSchema<String>() {   // 反序列化
                        @Override
                        public String deserialize(byte[] message) throws IOException {
                            if (message != null) {  // 针对空值处理防止报异常
                                return analysisToString(message, logType);
                            }
                            return null;
                        }

                        @Override
                        public boolean isEndOfStream(String nextElement) {
                            return false;
                        }

                        @Override
                        public TypeInformation<String> getProducedType() {
                            return Types.STRING;
                        }
                    })
                    .setProperty(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed")  // 事务隔离级别：读提交
                    .setProperty("security.protocol", "SSL")  // MSK 需要设置 SSL
                    .setProperty("max.poll.records", "5000")  // 每次拉取的最大记录数
                    .setProperty("session.timeout.ms", "60000")  // 消费 session 超时时长
                    .build();
        }
        return KafkaSource
                .<String>builder()
                .setBootstrapServers(kafkaBrokers)  // kafka服务器
                .setGroupId(groupId)  // 消费者组
                .setTopics(topic)  // 消费者主题
//                .setStartingOffsets(OffsetsInitializer.earliest())
                .setStartingOffsets(OffsetsInitializer.committedOffsets(OffsetResetStrategy.LATEST))  // 从以提交的偏移量开始消费，如果没有则从最新位置消费
                .setValueOnlyDeserializer(new DeserializationSchema<String>() {   // 反序列化
                    @Override
                    public String deserialize(byte[] message) throws IOException {
                        if (message != null) {  // 针对空值处理防止报异常
                            return new String(message, StandardCharsets.UTF_8);
                        }
                        return null;
                    }

                    @Override
                    public boolean isEndOfStream(String nextElement) {
                        return false;
                    }

                    @Override
                    public TypeInformation<String> getProducedType() {
                        return Types.STRING;
                    }
                })
                .setProperty(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed")  // 事务隔离级别：读提交
                .setProperty("session.timeout.ms", "60000")  // 消费 session 超时时长
                .setProperty("max.poll.records", "5000")  // 每次拉取的最大记录数
                .build();
    }
}
