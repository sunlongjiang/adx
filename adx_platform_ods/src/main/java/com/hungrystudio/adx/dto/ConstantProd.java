package com.hungrystudio.adx.dto;

/**
 * 生产环境：
 *    常用变量
 */
public class ConstantProd {
    // flink
    // checkpoint 间隔时长单位毫秒
    // block blast ios
    public static final long BLOCK_BLAST_IOS_FLINK_CHECKPOINT_INTERVAL = 120000L;  // 120s
    // block blast gp
    public static final long BLOCK_BLAST_GP_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // solitaire ios
    public static final long SOLITAIRE_IOS_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // solitaire gp
    public static final long SOLITAIRE_GP_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // sudoku ios
    public static final long SUDOKU_IOS_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // sudoku gp
    public static final long SUDOKU_GP_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // block journey ios
    public static final long BLOCK_JOURNEY_IOS_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // block journey gp
    public static final long BLOCK_JOURNEY_GP_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // match 3d gp
    public static final long MATCH_OUT_GP_FLINK_CHECKPOINT_INTERVAL = 5000L;
    // 消费原始日志的checkpoint 间隔时长单位毫秒
    public static final long BLOCK_BLAST_IOS_RAW_LOG_CONSUMER_FLINK_CHECKPOINT_INTERVAL = 30000L;
    public static final long BLOCK_BLAST_GP_RAW_LOG_CONSUMER_FLINK_CHECKPOINT_INTERVAL = 30000L;

    // checkpoint 存储父路径
    // checkpoint hdfs父路径
    public static final String FLINK_CHECKPOINT_HDFS_PARENT_PATH = "hdfs:///flink_checkpoint_prod/hungry_studio_lakehouse/";
    // checkpoint s3父路径
    public static final String FLINK_CHECKPOINT_S3_PARENT_PATH = "s3://hungry-studio-data-warehouse/lakehouse_conf/flink_checkpoint_prod/";

    // 非密点位及属性日志去重窗口时长
    // block blast ios
    public static final int BLOCK_BLAST_IOS_DUPLICATE_RETAIN_SECOND = 1800;  // 2小时
    // block blast gp
    public static final int BLOCK_BLAST_GP_DUPLICATE_RETAIN_SECOND = 3600;  // 2小时
    // solitaire ios
    public static final int SOLITAIRE_IOS_DUPLICATE_RETAIN_SECOND = 7200;  // 2小时
    // solitaire gp
    public static final int SOLITAIRE_GP_DUPLICATE_RETAIN_SECOND = 7200;  // 2小时
    // sudoku ios
    public static final int SUDOKU_IOS_DUPLICATE_RETAIN_SECOND = 7200;  // 2小时
    // sudoku gp
    public static final int SUDOKU_GP_DUPLICATE_RETAIN_SECOND = 7200;  // 2小时
    // block journey ios
    public static final int BLOCK_JOURNEY_IOS_DUPLICATE_RETAIN_SECOND = 7200;  // 2小时
    // block journey gp
    public static final int BLOCK_JOURNEY_GP_DUPLICATE_RETAIN_SECOND = 7200;  // 2小时
    // match 3d gp
    public static final int MATCH_OUT_GP_DUPLICATE_RETAIN_SECOND = 7200;  // 2小时



    // kafka
    // 原始日志所在的kafka服务器
    // 数仓SDK原始日志MSK
//    public static final String NEW_SDK_RAW_LOG_MSK_KAFKA_BROKERS = "b-2.shucangmskcluster2404.nj5jrh.c4.kafka.us-east-2.amazonaws.com:9094,b-1.shucangmskcluster2404.nj5jrh.c4.kafka.us-east-2.amazonaws.com:9094,b-3.shucangmskcluster2404.nj5jrh.c4.kafka.us-east-2.amazonaws.com:9094";
    public static final String NEW_SDK_RAW_LOG_MSK_KAFKA_BROKERS = "b-3.shucangmskios2410.vz0tpf.c6.kafka.us-east-2.amazonaws.com:9094,b-1.shucangmskios2410.vz0tpf.c6.kafka.us-east-2.amazonaws.com:9094,b-2.shucangmskios2410.vz0tpf.c6.kafka.us-east-2.amazonaws.com:9094";
    // 国外数数SDK原始日志kafka
    public static final String OLD_FOREIGN_SDK_RAW_LOG_KAFKA_BROKERS = "172.31.46.123:9092,172.31.35.139:9092,172.31.42.199:9092";
    // 国内数数SDK原始日志kafka
    public static final String OLD_NATIVE_SDK_RAW_LOG_KAFKA_BROKERS = "106.75.21.135:9092,117.50.96.10:9092,106.75.91.141:9092";

    // 从原始日志已处理后的日志所在 kafka服务器
    // block blast ios dwd层已处理后的非密事件日志kafka
    public static final String BLOCK_BLAST_IOS_DWD_SPARSE_EVENT_LOG_MSK_KAFKA_BROKERS = "b-1.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094,b-3.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094,b-2.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094";
//    public static final String BLOCK_BLAST_IOS_DWD_SPARSE_EVENT_LOG_MSK_KAFKA_BROKERS = "b-3.shucangmskios2410.vz0tpf.c6.kafka.us-east-2.amazonaws.com:9094,b-1.shucangmskios2410.vz0tpf.c6.kafka.us-east-2.amazonaws.com:9094,b-2.shucangmskios2410.vz0tpf.c6.kafka.us-east-2.amazonaws.com:9094";
    // block blast ios dwd层已处理后的密的事件日志kafka
    public static final String BLOCK_BLAST_IOS_DWD_DENSE_EVENT_LOG_MSK_KAFKA_BROKERS = "b-1.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094,b-3.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094,b-2.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094";
    // block blast ios dwd层已处理后的玩家属性日志kafka
    public static final String BLOCK_BLAST_IOS_DWD_USER_LOG_MSK_KAFKA_BROKERS = "b-1.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094,b-3.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094,b-2.shucangmskself2408.3gv1tg.c7.kafka.us-east-2.amazonaws.com:9094";

    // 从原始日志进行消费的消费组前缀
    // 数仓 SDK原始日志 消费者组前缀
    public static final String NEW_SDK_KAFKA_RAW_LOG_CONSUMER_GROUP_ID_PREFIX = "consumer_lakehouse_dwd_";
    // 国外数数 SDK 原始日志 消费者组前缀
    public static final String OLD_FOERIGN_SDK_KAFKA_RAW_LOG_CONSUMER_GROUP_ID_PREFIX = "consumer_lakehouse_dwd_";
    // 国内数数 SDK 原始日志 消费者组前缀
    public static final String OLD_NATIVE_SDK_KAFKA_RAW_LOG_CONSUMER_GROUP_ID_PREFIX = "consumer_lakehouse_dwd_";

    // 数仓SDK原始日志所在的kafka topic
    // block blast ios
    public static final String BLOCK_BLAST_IOS_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "lw-log-df147cd439514465b5b9e75c05a55ed5";
    // block blast gp
    public static final String BLOCK_BLAST_GP_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "lw-log-64188241f3a74ed5bdf928a1687dd18d";
    // solitaire ios
    public static final String SOLITAIRE_IOS_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "";
    // solitaire gp
    public static final String SOLITAIRE_GP_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "";
    // sudoku ios
    public static final String SUDOKU_IOS_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "";
    // sudoku gp
    public static final String SUDOKU_GP_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "";
    // block journey ios
    public static final String BLOCK_JOURNEY_IOS_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "";
    // block journey gp
    public static final String BLOCK_JOURNEY_GP_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "";
    // match 3d gp
    public static final String MATCH_OUT_GP_NEW_SDK_KAFKA_RAW_LOG_TOPIC = "";
    // 国外数数原始日志所在的kafka topic
    public static final String OLD_FOREIGN_KAFKA_RAW_LOG_TOPIC = "ta-data";
    // 国内数数原始日志所在的kafka topic
    public static final String OLD_NATIVE_KAFKA_RAW_LOG_TOPIC = "ta-data";

    // 海外 包括国外数数和数仓 SDK
    // dwd已处理后的非密事件存储的kafka topic
    // block blast ios
    public static final String BLOCK_BLAST_IOS_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-blast-ios-sparse-event-prod1";
    // block blast gp
    public static final String BLOCK_BLAST_GP_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-blast-gp-sparse-event-prod";
    // solitaire ios
    public static final String SOLITAIRE_IOS_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-solitaire-ios-sparse-event-prod";
    // solitaire gp
    public static final String SOLITAIRE_GP_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-solitaire-gp-sparse-event-prod";
    // sudoku ios
    public static final String SUDOKU_IOS_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-sudoku-ios-sparse-event-prod";
    // sudoku gp
    public static final String SUDOKU_GP_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-sudoku-gp-sparse-event-prod";
    // block journey ios
    public static final String BLOCK_JOURNEY_IOS_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-journey-ios-sparse-event-prod";
    // block journey gp
    public static final String BLOCK_JOURNEY_GP_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-journey-gp-sparse-event-prod";
    // match 3d gp
    public static final String MATCH_OUT_GP_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-match-gp-sparse-event-prod";
    // dwd已处理后的密的事件存储的kafka topic
    // block blast ios
    public static final String BLOCK_BLAST_IOS_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-blast-ios-dense-event-prod";
    // block blast gp
    public static final String BLOCK_BLAST_GP_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-blast-gp-dense-event-prod";
    // solitaire ios
    public static final String SOLITAIRE_IOS_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-solitaire-ios-dense-event-prod";
    // solitaire gp
    public static final String SOLITAIRE_GP_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-solitaire-gp-dense-event-prod";
    // sudoku ios
    public static final String SUDOKU_IOS_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-sudoku-ios-dense-event-prod";
    // sudoku gp
    public static final String SUDOKU_GP_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-sudoku-gp-dense-event-prod";
    // block journey ios
    public static final String BLOCK_JOURNEY_IOS_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-journey-ios-dense-event-prod";
    // block journey gp
    public static final String BLOCK_JOURNEY_GP_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-journey-gp-dense-event-prod";
    // match 3d gp
    public static final String MATCH_OUT_GP_DWD_DENSE_EVENT_TOPIC = "topic-lakehouse-dwd-match-gp-dense-event-prod";
    // 已处理后的玩家属性日志存储的kafka topic
    // block blast ios
    public static final String BLOCK_BLAST_IOS_DWD_USER_TOPIC = "topic-lakehouse-dwd-block-blast-ios-user-prod";
    // block blast gp
    public static final String BLOCK_BLAST_GP_DWD_USER_TOPIC = "topic-lakehouse-dwd-block-blast-gp-user-prod";
    // solitaire ios
    public static final String SOLITAIRE_IOS_DWD_USER_TOPIC = "topic-lakehouse-dwd-solitaire-ios-user-prod";
    // solitaire gp
    public static final String SOLITAIRE_GP_DWD_USER_TOPIC = "topic-lakehouse-dwd-solitaire-gp-user-prod";
    // sudoku ios
    public static final String SUDOKU_IOS_DWD_USER_TOPIC = "topic-lakehouse-dwd-sudoku-ios-user-prod";
    // sudoku gp
    public static final String SUDOKU_GP_DWD_USER_TOPIC = "topic-lakehouse-dwd-sudoku-gp-user-prod";
    // block journey ios
    public static final String BLOCK_JOURNEY_IOS_DWD_USER_TOPIC = "topic-lakehouse-dwd-block-journey-ios-user-prod";
    // block journey gp
    public static final String BLOCK_JOURNEY_GP_DWD_USER_TOPIC = "topic-lakehouse-dwd-block-journey-gp-user-prod";
    // match 3d gp
    public static final String MATCH_OUT_GP_DWD_USER_TOPIC = "topic-lakehouse-dwd-match-gp-user-prod";

    // native 国内数数
    // dwd已处理后的非密事件存储的kafka topic
    // block blast ios
    public static final String BLOCK_BLAST_IOS_NATIVE_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-blast-ios-native-sparse-event-prod";
    // block blast gp
    public static final String BLOCK_BLAST_GP_NATIVE_DWD_SPARSE_EVENT_TOPIC = "topic-lakehouse-dwd-block-blast-gp-native-sparse-event-prod";
    // 已处理后的玩家属性日志存储的kafka topic
    // block blast ios
    public static final String BLOCK_BLAST_IOS_NATIVE_DWD_USER_TOPIC = "topic-lakehouse-dwd-block-blast-ios-native-user-prod";
    // block blast gp
    public static final String BLOCK_BLAST_GP_NATIVE_DWD_USER_TOPIC = "topic-lakehouse-dwd-block-blast-gp-native-user-prod";

    // mysql
    public static final String MYSQL_DATABASE = "sc_google_firebase";
    public static final String MYSQL_WHITE_EVENT_AND_COLUMN_TABLE = "bgd_bi_event_attr";
    public static final String MYSQL_WHITE_EVENT_TABLE = "bgd_bi_events";
    public static final String MYSQL_WHITE_DUP_CORE_EVENT_TABLE = "lakehuose_meta_white_dup_core_event_prod";
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    // read config
    public static final String MYSQL_READ_HOST = "shucang-google-firebase-db.cluster-ro-cjmimqdxiiih.us-east-2.rds.amazonaws.com";
    public static final int MYSQL_READ_PORT = 3306;
    public static final String MYSQL_READ_USER = "sc_google_firebase_slt";
    public static final String MYSQL_READ_PASSWORD = "tkH1nQSDY538FkVr";
    public static final String MYSQL_READ_JDBC_URL = "jdbc:mysql://shucang-google-firebase-db.cluster-ro-cjmimqdxiiih.us-east-2.rds.amazonaws.com:3306/sc_google_firebase";
    // write config
    public static final String MYSQL_WRITE_HOST = "shucang-google-firebase-db.cluster-cjmimqdxiiih.us-east-2.rds.amazonaws.com";
    public static final int MYSQL_WRITE_PORT = 3306;
    public static final String MYSQL_WRITE_USER = "sc_google_firebase_adm";
    public static final String MYSQL_WRITE_PASSWORD = "8D7cpFR0SrTSOKzp";
    public static final String MYSQL_WRITE_JDBC_URL = "jdbc:mysql://shucang-google-firebase-db.cluster-cjmimqdxiiih.us-east-2.rds.amazonaws.com:3306/sc_google_firebase";


    // 业务
    public static final long BLOCK_BLAST_GP_GAME_ID = 10000002;
    public static final long BLOCK_BLAST_IOS_GAME_ID = 10000003;
    public static final long SOLITAIRE_GP_GAME_ID = 10000004;
    public static final long SOLITAIRE_IOS_GAME_ID = 10000005;
    public static final long SUDOKU_GP_GAME_ID = 10000006;
    public static final long SUDOKU_IOS_GAME_ID = 10000007;
    public static final long BLOCK_JOURNEY_GP_GAME_ID = 10000008;
    public static final long MATCH_OUT_GP_GAME_ID = 10000009;
    public static final long BLOCK_JOURNEY_IOS_GAME_ID = 10000010;

    public static final String BLOCK_BLAST_IOS_APP_ID = "";
    public static final String BLOCK_BLAST_GP_APP_ID = "";
    public static final String SOLITAIRE_IOS_APP_ID = "";
    public static final String SOLITAIRE_GP_APP_ID = "";
    public static final String SUDOKU_IOS_APP_ID = "";
    public static final String SUDOKU_GP_APP_ID = "";
    public static final String BLOCK_JOURNEY_IOS_APP_ID = "";
    public static final String BLOCK_JOURNEY_GP_APP_ID = "";
    public static final String MATCH_OUT_GP_APP_ID = "";


    public static final String BLOCK_BLAST_IOS_BUNDLE_ID = "";
    public static final String BLOCK_BLAST_GP_BUNDLE_ID = "";
    public static final String SOLITAIRE_IOS_BUNDLE_ID = "";
    public static final String SOLITAIRE_GP_BUNDLE_ID = "";
    public static final String SUDOKU_IOS_BUNDLE_ID = "";
    public static final String SUDOKU_GP_BUNDLE_ID = "";
    public static final String BLOCK_JOURNEY_IOS_BUNDLE_ID = "";
    public static final String BLOCK_JOURNEY_GP_BUNDLE_ID = "";
    public static final String MATCH_OUT_GP_BUNDLE_ID = "";

    public static final String BLOCK_BLAST_IOS_APP_VERSION_THRESHOLD = "3.5.4";
    public static final String BLOCK_BLAST_GP_APP_VERSION_THRESHOLD = "4.1.2";
    public static final String SOLITAIRE_IOS_APP_VERSION_THRESHOLD = "";
    public static final String SOLITAIRE_GP_APP_VERSION_THRESHOLD = "1.7.4";
    public static final String SUDOKU_IOS_APP_VERSION_THRESHOLD = "";
    public static final String SUDOKU_GP_APP_VERSION_THRESHOLD = "2.3.1";
    public static final String BLOCK_JOURNEY_IOS_APP_VERSION_THRESHOLD = "";
    public static final String BLOCK_JOURNEY_GP_APP_VERSION_THRESHOLD = "1.2.7";
    public static final String MATCH_OUT_GP_APP_VERSION_THRESHOLD = "";

    // 国外数数属性日志时区
    public static final int BLOCK_BLAST_IOS_OLD_FOREIGN_ZONEOFFSET = 0;
    public static final int BLOCK_BLAST_GP_OLD_FOREIGN_ZONEOFFSET = 8;
    // 国内数数属性日志时区
    public static final int BLOCK_BLAST_IOS_OLD_NATIVE_ZONEOFFSET = 0;
    public static final int BLOCK_BLAST_GP_OLD_NATIVE_ZONEOFFSET = 8;


    // ip库
    public static final String IP_DB_AWS_S3_REGIN = "us-east-2";
    public static final String IP_DB_S3_BUCKET_NAME = "hungry-studio-data-warehouse";
    public static final String IP_DB_PATH_KEY = "ods/data_type=geoip/ipplus360/IP_city_single_WGS84.awdb";



}
