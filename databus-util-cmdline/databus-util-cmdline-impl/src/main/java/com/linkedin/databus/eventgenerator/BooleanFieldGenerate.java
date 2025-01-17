package com.linkedin.databus.eventgenerator;
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


import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericRecord;

public class BooleanFieldGenerate extends SchemaFiller{

  public BooleanFieldGenerate(Field field)
  {
    super(field);
  }

  @Override
  public void writeToRecord(GenericRecord record) throws UnknownTypeException
  {
      record.put(field.name(), generateBoolean());
  }

  @Override
  public Object generateRandomObject() throws UnknownTypeException
  {
    return generateBoolean();
  }

  public boolean generateBoolean()
  {
    return randGenerator.getNextBoolean();
  }
}
