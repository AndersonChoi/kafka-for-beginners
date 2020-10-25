package com.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;

public class TemperatureProducer {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureProducer.class);

    private static String TOPIC_NAME = "live.kafka.sensor";
    private static String BOOTSTRAP_SERVERS = "my-kafka:9092";

    public static void main(String[] args) {


        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        while (true) {
            try {
                Random rand = new Random();
                String data = Integer.toString(rand.nextInt(10));
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, data);
                producer.send(record);
                logger.info("Send to " + TOPIC_NAME + " | data : " + data);
                Thread.sleep(10000);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}