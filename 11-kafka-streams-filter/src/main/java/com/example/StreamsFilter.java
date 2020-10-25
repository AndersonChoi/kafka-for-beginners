
package com.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class StreamsFilter {

    private static String APPLICATION_NAME = "metric-streams-application";
    private static String BOOTSTRAP_SERVERS = "my-kafka:9092";
    private static String TOPIC_NAME = "test";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> testStream = builder.stream(TOPIC_NAME);
        KStream<String, String> filteredStream = testStream.filter((key, value) -> value.length() > 5);

        filteredStream.to("test2");

        KafkaStreams streams;
        streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }
}