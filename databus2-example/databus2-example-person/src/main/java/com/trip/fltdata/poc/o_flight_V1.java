/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.trip.fltdata.poc;

@SuppressWarnings("all")
/** Auto-generated Avro schema for sy$o_flight. Generated at Feb 14, 2022 01:00:16 AM EST */
public class o_flight_V1 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = org.apache.avro.Schema.parse("{\"type\":\"record\",\"name\":\"o_flight_V1\",\"namespace\":\"com.trip.fltdata.poc\",\"fields\":[{\"name\":\"TXN\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=TXN;dbFieldPosition=0;dbFieldType=LONG;\"},{\"name\":\"OID\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=OID;dbFieldPosition=1;dbFieldType=LONG;\"},{\"name\":\"UNAME\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=UNAME;dbFieldPosition=2;dbFieldType=VARCHAR2;\"},{\"name\":\"UAGE\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=UAGE;dbFieldPosition=3;dbFieldType=LONG;\"}],\"meta\":\"dbFieldName=sy$o_flight;dbFieldType=sy$o_flight;\"}");
  public java.lang.Long TXN;
  public java.lang.Long OID;
  public java.lang.CharSequence UNAME;
  public java.lang.Long UAGE;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return TXN;
    case 1: return OID;
    case 2: return UNAME;
    case 3: return UAGE;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: TXN = (java.lang.Long)value$; break;
    case 1: OID = (java.lang.Long)value$; break;
    case 2: UNAME = (java.lang.CharSequence)value$; break;
    case 3: UAGE = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
}
