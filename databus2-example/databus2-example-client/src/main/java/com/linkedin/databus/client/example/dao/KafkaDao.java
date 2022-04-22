package com.linkedin.databus.client.example.dao;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class KafkaDao {

    public static void insert(String tableName, String targetTopicName, Map rowData) throws IOException {
        System.out.println(String.format("Insert into %s from table %s", targetTopicName, tableName));
        System.out.println(new ObjectMapper().writeValueAsString(rowData));
    }
}
