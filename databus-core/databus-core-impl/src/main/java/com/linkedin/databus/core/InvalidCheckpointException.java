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
package com.linkedin.databus.core;

/**
 * An exception class for invalid checkpoints
 */
public class InvalidCheckpointException extends DatabusRuntimeException
{
  private static final long serialVersionUID = 1L;
  private final Checkpoint _checkpoint;

  public InvalidCheckpointException(String message, Checkpoint cp)
  {
    super(message + ": " + cp);
    _checkpoint = cp;
  }

  protected Checkpoint getCheckpoint()
  {
    return _checkpoint;
  }
}
