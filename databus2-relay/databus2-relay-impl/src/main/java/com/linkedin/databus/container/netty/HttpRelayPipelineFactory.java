/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.linkedin.databus.container.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.linkedin.databus2.core.container.netty.HttpServerPipelineFactory;

/**
 * Extends the {@link HttpServerPipelineFactory} with relay-specific handlers;
 */
public class HttpRelayPipelineFactory implements ChannelPipelineFactory {

	private final HttpRelay _relay;
	private ChannelPipelineFactory _oldPipelineFactory;
	
	public HttpRelayPipelineFactory(HttpRelay relay, ChannelPipelineFactory oldPipelineFactory) {
		super();
		_relay = relay;
		_oldPipelineFactory = oldPipelineFactory;
	}
	
    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = _oldPipelineFactory.getPipeline();

        RelayStatisticsCollectingHandler relayStatsHandler =
            new RelayStatisticsCollectingHandler(_relay);
        pipeline.addBefore("databusRequestRunner", "relayStatsCollector", relayStatsHandler);
        
        return pipeline;
    }
}
