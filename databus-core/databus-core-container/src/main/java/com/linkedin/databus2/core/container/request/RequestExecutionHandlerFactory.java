package com.linkedin.databus2.core.container.request;
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


import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * The factory interface for execution handlers which listen on a Netty pipeline for a
 * particular type of command objects and execute the commands represented by the objects.*/
public interface RequestExecutionHandlerFactory
{

  /** Creates a handler for the execution of a particular types of request */
  SimpleChannelHandler createHandler(Channel channel);

}
