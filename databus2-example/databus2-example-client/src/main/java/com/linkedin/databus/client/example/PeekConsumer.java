package com.linkedin.databus.client.example;
/*
 *
 * Copyright 2013 LinkedIn Corp. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

import com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer;
import com.linkedin.databus.client.pub.ConsumerCallbackResult;
import com.linkedin.databus.client.pub.DbusEventDecoder;
import com.linkedin.databus.core.DbusEvent;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.log4j.Logger;

public class PeekConsumer extends AbstractDatabusCombinedConsumer {

    public static final Logger LOG = Logger.getLogger(PeekConsumer.class.getName());
    private String tableName;
    private String peekColumnName;

    public PeekConsumer(ConsumerCallbackResult defaultStreamAnswer,
        ConsumerCallbackResult defaultBootstrapAnswer, String tableName,
        String peekColumnName) {
        super(defaultStreamAnswer, defaultBootstrapAnswer);
        this.tableName = tableName;
        this.peekColumnName = peekColumnName;
    }

    public PeekConsumer(String tableName, String peekColumnName) {
        this.tableName = tableName;
        this.peekColumnName = peekColumnName;
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

    private ConsumerCallbackResult processEvent(DbusEvent event,
        DbusEventDecoder eventDecoder) {
        GenericRecord decodedEvent = eventDecoder.getGenericRecord(event, null);
        try {
            Utf8 peekColVal = (Utf8) decodedEvent.get(this.peekColumnName);
            LOG.info(String.format("table %s changed! peek col %s values is:%s", this.tableName,
                this.peekColumnName, peekColVal));
        } catch (Exception e) {
            LOG.error("error decoding event ", e);
            return ConsumerCallbackResult.ERROR;
        }

        return ConsumerCallbackResult.SUCCESS;
    }

}

