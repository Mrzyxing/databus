package com.linkedin.databus.client.example.dao;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class KafkaDao {
    public static final Logger LOG = Logger.getLogger(KafkaDao.class);

    private static Properties config() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.211.2.62:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("enable.idompotence", "true");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // sasl
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,"SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM,"PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG,"org.apache.kafka.common.security.plain.PlainLoginModule required username=\"admin\" password=\"npU#UTXpJWQ3aLkE\"");
        return props;
    }

    public static void insert(String tableName, String targetTopicName, Map rowData) throws IOException {
        Producer<String, String> producer = new KafkaProducer<>(config());
        producer.send(
                new ProducerRecord<>(targetTopicName, null, new ObjectMapper().writeValueAsString(rowData))
                , (metadata, exception) -> {
                    if (exception!=null){
                        // do nothing
                    }
                }
        );
        producer.close();
    }
}
