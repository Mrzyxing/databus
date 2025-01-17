package com.linkedin.databus.client.example;

import com.google.common.collect.Maps;
import com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer;
import com.linkedin.databus.client.example.dao.KafkaDao;
import com.linkedin.databus.client.pub.ConsumerCallbackResult;
import com.linkedin.databus.client.pub.DbusEventDecoder;
import com.linkedin.databus.core.DbusEvent;
import org.apache.avro.generic.GenericRecord;
import org.apache.log4j.Logger;

import java.util.Map;

public class SyncConsumer extends AbstractDatabusCombinedConsumer {

    public static final Logger LOG = Logger.getLogger(SyncConsumer.class.getName());
    private String tableName;
    private String targetTopicName;
    private Map<String,String> optionKafkaConfigs;

    public SyncConsumer(ConsumerCallbackResult defaultStreamAnswer,
                        ConsumerCallbackResult defaultBootstrapAnswer, String tableName,
                        String targetTopicName) {
        super(defaultStreamAnswer, defaultBootstrapAnswer);
        this.tableName = tableName;
        this.targetTopicName = targetTopicName;
    }

    public SyncConsumer(String tableName, String targetTopicName, Map<String, String> kfConfigs) {
        this.tableName = tableName;
        this.targetTopicName = targetTopicName;
        this.optionKafkaConfigs = kfConfigs;
    }

    private ConsumerCallbackResult processEvent(DbusEvent event,
                                                DbusEventDecoder eventDecoder) {
        GenericRecord decodedEvent = eventDecoder.getGenericRecord(event, null);
        try {
            Map<String, Object> rowData = Maps.newHashMap();
            decodedEvent.getSchema().getFields().forEach(field -> {
                rowData.put(field.name(),
                        decodedEvent.get(field.name()) == null ? null : decodedEvent.get(field.name()).toString());
            });
            KafkaDao.insert(tableName, targetTopicName, rowData,optionKafkaConfigs);
        } catch (Exception e) {
            LOG.error("error decoding event ", e);
            return ConsumerCallbackResult.ERROR;
        }

        return ConsumerCallbackResult.SUCCESS;
    }

    @Override
    public ConsumerCallbackResult onDataEvent(DbusEvent event,
                                              DbusEventDecoder eventDecoder) {
        return processEvent(event, eventDecoder);
    }

    @Override
    public ConsumerCallbackResult onBootstrapEvent(DbusEvent event,
                                                   DbusEventDecoder eventDecoder) {
        return processEvent(event, eventDecoder);
    }
}
