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

import com.google.common.base.Splitter;
import com.linkedin.databus.client.DatabusHttpClientImpl;
import com.linkedin.databus.client.example.ClientConfig.ClientConfigBuilder;
import com.linkedin.databus.client.pub.DatabusCombinedConsumer;
import com.linkedin.databus2.core.container.netty.ServerContainer;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;

public class PeekClientMain {

    private static void createClient(ClientConfig config) throws Exception {

        DatabusHttpClientImpl.Config configBuilder = new DatabusHttpClientImpl.Config();

        if (config.getContainHttpPort() != null) {
            ServerContainer.Config containerCfg = new ServerContainer.Config();
            containerCfg.setHttpPort(config.getContainHttpPort());
            containerCfg.getJmx().setJmxServicePort(containerCfg.getJmx().getJmxServicePort());
            configBuilder.setContainer(containerCfg);
        }
        // Try to connect to a relay on localhost
        configBuilder.getRuntime().getRelay(config.getRelayName()).setHost("localhost");
        configBuilder.getRuntime().getRelay(config.getRelayName()).setPort(11115);
        configBuilder.getRuntime().getRelay(config.getRelayName())
            .setSources(config.getSourceName());

        // Instantiate a client using command-line parameters if any
        DatabusHttpClientImpl client = DatabusHttpClientImpl
            .createFromCli(config.getArgs(), configBuilder);
        int port = client.getContainerStaticConfig().getHttpPort();

        // register callbacks
        DatabusCombinedConsumer consumer = new PeekConsumer(config.getTableName(),
            config.getPeekColumnName());
        client.registerDatabusStreamListener(consumer, null, config.getSourceName());
        client.registerDatabusBootstrapListener(consumer, null, config.getSourceName());

        // fire off the Databus client
        client.startAndBlock();
    }

    static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        ClientConfig.ClientConfigBuilder builder = ClientConfig.ClientConfigBuilder.aClientConfig();
        builder.withArgs(args);
        List<String> lines = FileUtils.readLines(new File("./conf/cli.config"));
        lines.forEach(line -> {
            if (line.charAt(0) == '#') {
                System.out.println("Skip line:" + line);
                return;
            }
            List<String> configs = Splitter.on("@").splitToList(line);
            ClientConfigBuilder clientConfigBuilder = builder.but()
                .withRelayName(configs.get(0))
                .withSourceName(configs.get(1))
                .withTableName(configs.get(2))
                .withPeekColumnName(configs.get(3));
            if (configs.size() == 5) {
                clientConfigBuilder.withContainHttpPort(NumberUtils.toInt(configs.get(4)));
            }
            ClientConfig clientConfig = clientConfigBuilder.build();
            System.out.println("Submit client " + clientConfig.getTableName());
            threadPool.submit(() -> {
                try {
                    createClient(clientConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }
}

