/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.linkedin.databus.monitoring.events;
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


@SuppressWarnings("all")
/** Statistics for connections to a databus database source */
public class DbusDbSourceConnectionStatsEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = org.apache.avro.Schema.parse("{\"type\":\"record\",\"name\":\"DbusDbSourceConnectionStatsEvent\",\"namespace\":\"com.linkedin.databus.monitoring.events\",\"fields\":[{\"name\":\"relayId\",\"type\":\"int\",\"doc\":\"The id of the relay that generated the event\"},{\"name\":\"timestampLastResetMs\",\"type\":\"long\",\"doc\":\"unix timestamp of the last reset() call\"},{\"name\":\"timeSinceLastResetMs\",\"type\":\"long\",\"doc\":\"time in ms since the last reset() call\"},{\"name\":\"numOpenDbConns\",\"type\":\"long\",\"doc\":\"number of DB connections open\"},{\"name\":\"numClosedDbConns\",\"type\":\"long\",\"doc\":\"number of DB connections closed\"},{\"name\":\"timestampLastDbConnOpenMs\",\"type\":\"long\",\"doc\":\"the timestamp of the last DB connection open operation\"},{\"name\":\"timestampLastDbConnCloseMs\",\"type\":\"long\",\"doc\":\"the timestamp of the last DB connection close operation \"},{\"name\":\"timeClosedDbConnLifeMs\",\"type\":\"long\",\"doc\":\"total lifespan of closed DB connections \"},{\"name\":\"timeOpenDbConnLifeMs\",\"type\":\"long\",\"doc\":\"total lifespan of currently open DB connections \"},{\"name\":\"numRowsUpdated\",\"type\":\"long\",\"doc\":\"number of updated DB rows received\"}]}");
  /** The id of the relay that generated the event */
  public int relayId;
  /** unix timestamp of the last reset() call */
  public long timestampLastResetMs;
  /** time in ms since the last reset() call */
  public long timeSinceLastResetMs;
  /** number of DB connections open */
  public long numOpenDbConns;
  /** number of DB connections closed */
  public long numClosedDbConns;
  /** the timestamp of the last DB connection open operation */
  public long timestampLastDbConnOpenMs;
  /** the timestamp of the last DB connection close operation  */
  public long timestampLastDbConnCloseMs;
  /** total lifespan of closed DB connections  */
  public long timeClosedDbConnLifeMs;
  /** total lifespan of currently open DB connections  */
  public long timeOpenDbConnLifeMs;
  /** number of updated DB rows received */
  public long numRowsUpdated;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return relayId;
    case 1: return timestampLastResetMs;
    case 2: return timeSinceLastResetMs;
    case 3: return numOpenDbConns;
    case 4: return numClosedDbConns;
    case 5: return timestampLastDbConnOpenMs;
    case 6: return timestampLastDbConnCloseMs;
    case 7: return timeClosedDbConnLifeMs;
    case 8: return timeOpenDbConnLifeMs;
    case 9: return numRowsUpdated;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: relayId = (java.lang.Integer)value$; break;
    case 1: timestampLastResetMs = (java.lang.Long)value$; break;
    case 2: timeSinceLastResetMs = (java.lang.Long)value$; break;
    case 3: numOpenDbConns = (java.lang.Long)value$; break;
    case 4: numClosedDbConns = (java.lang.Long)value$; break;
    case 5: timestampLastDbConnOpenMs = (java.lang.Long)value$; break;
    case 6: timestampLastDbConnCloseMs = (java.lang.Long)value$; break;
    case 7: timeClosedDbConnLifeMs = (java.lang.Long)value$; break;
    case 8: timeOpenDbConnLifeMs = (java.lang.Long)value$; break;
    case 9: numRowsUpdated = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
}
