package com.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MongoDbConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MongoDbConsumer.class);

    // 1. 토픽, 클러스터 이름, 컨슈머 그룹 설정
    private static String TOPIC_NAME = "live.kafka.sensor";
    private static String BOOTSTRAP_SERVERS = "my-kafka:9092";
    private static String GROUP_ID = ;

    public static void main(String[] args) {

        MongoDb mongoDb = new MongoDb();

        // 2. 카프카 컨슈머 인스턴스 생성을 위한 설정


        // 3. 카프카 컨슈머 생성


        // 4. 컨슈머, 토픽 구독


        // 5. 컨슈머, polling 구문 작성
        // mongoDB에 데이터를 보내는 메서도 : mongoDb.putData("홍길동", "14");


    }
}