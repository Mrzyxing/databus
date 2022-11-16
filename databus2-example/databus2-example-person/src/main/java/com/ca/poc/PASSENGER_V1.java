/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.ca.poc;

@SuppressWarnings("all")
/** Auto-generated Avro schema for sy$passenger. Generated at Feb 11, 2022 02:15:02 PM CST */
public class PASSENGER_V1 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = org.apache.avro.Schema.parse("{\"type\":\"record\",\"name\":\"PASSENGER_V1\",\"namespace\":\"com.ca.poc\",\"fields\":[{\"name\":\"TXN\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=TXN;dbFieldPosition=0;dbFieldType=LONG;\"},{\"name\":\"PK_FLYERID\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=PK_FLYERID;dbFieldPosition=1;dbFieldType=LONG;\"},{\"name\":\"USERID\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=USERID;dbFieldPosition=2;dbFieldType=LONG;\"},{\"name\":\"SSR2\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=SSR2;dbFieldPosition=3;dbFieldType=CHAR;\"},{\"name\":\"SSR1\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=SSR1;dbFieldPosition=4;dbFieldType=CHAR;\"},{\"name\":\"BIRTHDAY\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=BIRTHDAY;dbFieldPosition=5;dbFieldType=DATE;\"},{\"name\":\"NATIONALITY\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=NATIONALITY;dbFieldPosition=6;dbFieldType=CHAR;\"},{\"name\":\"NATION\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=NATION;dbFieldPosition=7;dbFieldType=CHAR;\"},{\"name\":\"D_CITY\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=D_CITY;dbFieldPosition=8;dbFieldType=VARCHAR2;\"},{\"name\":\"D_STATE\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=D_STATE;dbFieldPosition=9;dbFieldType=VARCHAR2;\"},{\"name\":\"PASSPORTISSUECOUNTRY\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=PASSPORTISSUECOUNTRY;dbFieldPosition=10;dbFieldType=CHAR;\"},{\"name\":\"EXPIRY\",\"type\":[\"null\",\"long\"],\"default\":null,\"meta\":\"dbFieldName=EXPIRY;dbFieldPosition=11;dbFieldType=DATE;\"},{\"name\":\"FFPNUMBER\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=FFPNUMBER;dbFieldPosition=12;dbFieldType=VARCHAR2;\"},{\"name\":\"FFPTYPE\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=FFPTYPE;dbFieldPosition=13;dbFieldType=CHAR;\"},{\"name\":\"IDNUMBER\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=IDNUMBER;dbFieldPosition=14;dbFieldType=VARCHAR2;\"},{\"name\":\"IDTYPE\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=IDTYPE;dbFieldPosition=15;dbFieldType=CHAR;\"},{\"name\":\"PAXTYPE\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=PAXTYPE;dbFieldPosition=16;dbFieldType=CHAR;\"},{\"name\":\"EMAILADDR\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=EMAILADDR;dbFieldPosition=17;dbFieldType=CHAR;\"},{\"name\":\"MOBILEPHONE\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=MOBILEPHONE;dbFieldPosition=18;dbFieldType=CHAR;\"},{\"name\":\"MOBILEAREACODE\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=MOBILEAREACODE;dbFieldPosition=19;dbFieldType=CHAR;\"},{\"name\":\"MOBILECOUNTRYCODE\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=MOBILECOUNTRYCODE;dbFieldPosition=20;dbFieldType=CHAR;\"},{\"name\":\"GIVENNAMECN\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=GIVENNAMECN;dbFieldPosition=21;dbFieldType=VARCHAR2;\"},{\"name\":\"FAMILYNAMECN\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=FAMILYNAMECN;dbFieldPosition=22;dbFieldType=VARCHAR2;\"},{\"name\":\"GIVENNAMEEN\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=GIVENNAMEEN;dbFieldPosition=23;dbFieldType=VARCHAR2;\"},{\"name\":\"FAMILYNAMEEN\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=FAMILYNAMEEN;dbFieldPosition=24;dbFieldType=VARCHAR2;\"},{\"name\":\"GENDER\",\"type\":[\"null\",\"string\"],\"default\":null,\"meta\":\"dbFieldName=GENDER;dbFieldPosition=25;dbFieldType=CHAR;\"}],\"meta\":\"dbFieldName=sy$passenger;dbFieldType=sy$passenger;\"}");
  public java.lang.Long TXN;
  public java.lang.Long PK_FLYERID;
  public java.lang.Long USERID;
  public java.lang.CharSequence SSR2;
  public java.lang.CharSequence SSR1;
  public java.lang.Long BIRTHDAY;
  public java.lang.CharSequence NATIONALITY;
  public java.lang.CharSequence NATION;
  public java.lang.CharSequence D_CITY;
  public java.lang.CharSequence D_STATE;
  public java.lang.CharSequence PASSPORTISSUECOUNTRY;
  public java.lang.Long EXPIRY;
  public java.lang.CharSequence FFPNUMBER;
  public java.lang.CharSequence FFPTYPE;
  public java.lang.CharSequence IDNUMBER;
  public java.lang.CharSequence IDTYPE;
  public java.lang.CharSequence PAXTYPE;
  public java.lang.CharSequence EMAILADDR;
  public java.lang.CharSequence MOBILEPHONE;
  public java.lang.CharSequence MOBILEAREACODE;
  public java.lang.CharSequence MOBILECOUNTRYCODE;
  public java.lang.CharSequence GIVENNAMECN;
  public java.lang.CharSequence FAMILYNAMECN;
  public java.lang.CharSequence GIVENNAMEEN;
  public java.lang.CharSequence FAMILYNAMEEN;
  public java.lang.CharSequence GENDER;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return TXN;
    case 1: return PK_FLYERID;
    case 2: return USERID;
    case 3: return SSR2;
    case 4: return SSR1;
    case 5: return BIRTHDAY;
    case 6: return NATIONALITY;
    case 7: return NATION;
    case 8: return D_CITY;
    case 9: return D_STATE;
    case 10: return PASSPORTISSUECOUNTRY;
    case 11: return EXPIRY;
    case 12: return FFPNUMBER;
    case 13: return FFPTYPE;
    case 14: return IDNUMBER;
    case 15: return IDTYPE;
    case 16: return PAXTYPE;
    case 17: return EMAILADDR;
    case 18: return MOBILEPHONE;
    case 19: return MOBILEAREACODE;
    case 20: return MOBILECOUNTRYCODE;
    case 21: return GIVENNAMECN;
    case 22: return FAMILYNAMECN;
    case 23: return GIVENNAMEEN;
    case 24: return FAMILYNAMEEN;
    case 25: return GENDER;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: TXN = (java.lang.Long)value$; break;
    case 1: PK_FLYERID = (java.lang.Long)value$; break;
    case 2: USERID = (java.lang.Long)value$; break;
    case 3: SSR2 = (java.lang.CharSequence)value$; break;
    case 4: SSR1 = (java.lang.CharSequence)value$; break;
    case 5: BIRTHDAY = (java.lang.Long)value$; break;
    case 6: NATIONALITY = (java.lang.CharSequence)value$; break;
    case 7: NATION = (java.lang.CharSequence)value$; break;
    case 8: D_CITY = (java.lang.CharSequence)value$; break;
    case 9: D_STATE = (java.lang.CharSequence)value$; break;
    case 10: PASSPORTISSUECOUNTRY = (java.lang.CharSequence)value$; break;
    case 11: EXPIRY = (java.lang.Long)value$; break;
    case 12: FFPNUMBER = (java.lang.CharSequence)value$; break;
    case 13: FFPTYPE = (java.lang.CharSequence)value$; break;
    case 14: IDNUMBER = (java.lang.CharSequence)value$; break;
    case 15: IDTYPE = (java.lang.CharSequence)value$; break;
    case 16: PAXTYPE = (java.lang.CharSequence)value$; break;
    case 17: EMAILADDR = (java.lang.CharSequence)value$; break;
    case 18: MOBILEPHONE = (java.lang.CharSequence)value$; break;
    case 19: MOBILEAREACODE = (java.lang.CharSequence)value$; break;
    case 20: MOBILECOUNTRYCODE = (java.lang.CharSequence)value$; break;
    case 21: GIVENNAMECN = (java.lang.CharSequence)value$; break;
    case 22: FAMILYNAMECN = (java.lang.CharSequence)value$; break;
    case 23: GIVENNAMEEN = (java.lang.CharSequence)value$; break;
    case 24: FAMILYNAMEEN = (java.lang.CharSequence)value$; break;
    case 25: GENDER = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
}