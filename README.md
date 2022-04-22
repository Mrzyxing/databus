# DBA run on Oracle
打包一下db/ca_databus_poc给DBA

```
cd db/ca_databus_poc

./alter.sh tdp13online ACEPORTAL airchina CMSDT /oracle/oradata/tdp13online/ T_FRN_USERPROFILE,PASSENGER  
./db/oracle/bin/cleanSchema.sh ACEPORTAL/airchina@tdp13online T_FRN_USERPROFILE,PASSENGER

# only for test databus origin case
./poc.sh
```

# Dev config schema of Databus on server

```
# only allowed after assemble
cd  build/databus2-cmdline-tools-pkg/distribution
./bin/dbus2-avro-schema-gen.sh -avroOutDir ./avro/ -avroOutVersion 1 -javaOutDir ./java/ -namespace com.trip.fltdata.poc.person -recordName Person -viewName "sy\$person" -userName databus -password databus -database jdbc:oracle:thin:@192.168.56.101:1521:helowin
cp -r avro/* ~/workspace/databus/databus2-example/databus2-example-relay-pkg/schemas_registry/
cp -r java/com/trip/fltdata/poc/* ~/workspace/databus/databus2-example/databus2-example-person/src/main/java/com/trip/fltdata/poc/
```
> avsc文件中的meta字段一定要加pk！自动生成的漏掉此信息导致无法动realy

# Dev compile Databus on PC

```
./reset_assemble.sh
```

# Dev deploy Databus on server
打包build下的example内容 分别拉起relay和client即可
```
cd build/databus2-example-relay-pkg/distributions/
tar -zxvf databus2-example-relay-pkg-2.0.0.tar.gz
# 这里的person仅仅是在脚本中用于判定启动那个class
./bin/start-example-relay.sh person

cd ../../databus2-example-client-pkg/distributions/
tar -zxvf databus2-example-client-pkg-2.0.0.tar.gz
./bin/start-example-client.sh person
```


