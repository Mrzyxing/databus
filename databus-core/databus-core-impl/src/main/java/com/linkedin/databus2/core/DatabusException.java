/*
 * $Id: DatabusException.java 168967 2011-02-25 21:56:00Z cbotev $
 */
package com.linkedin.databus2.core;
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


/**
 * Generic Databus exception
 */
public class DatabusException
    extends Exception
{
  private static final long serialVersionUID = 1L;

  public DatabusException()
  {
    super();
  }

  public DatabusException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public DatabusException(String message)
  {
    super(message);
  }

  public DatabusException(Throwable cause)
  {
    super(cause);
  }
}
