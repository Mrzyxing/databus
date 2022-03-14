#!/bin/sh

:<<COMMENT
Author: zyxing@trip.com on 2022/02/10
Desc:
  Run this script as user oracle!

COMMENT

usage(){
  cat << USAGE
Usage: alter.sh sid_name u_name u_pwd tbs_name tbs_path source_tbs
 sid_name     oracle sid
 u_name       source table onwer
 u_pwd        source table password
 tbs_name     source table in which table space
 tbs_path     locatoin of source table
 source_tbs   source table name,split on comma
example: ./alter.sh prd wuser wuserpwd tbs_order /path/to/dbf o_orders,o_flight
USAGE
}

if test $# != 6 ;then
  usage
  exit 1
fi

sid_name=$1
uname="$2"
upwd="$3"
tbs_name=$4
tbs_path=$5
source_tbs=${6//,/ }

# first gen source for example name of b2c
if test -d ./alter_source;then
  echo "Clean alter_source configuration"
  rm -rdf alter_source
else
  echo "Will create ./alter_source"
fi

mkdir alter_source/b2c -p

echo "Writing table space info into alter_source/b2c/tablespace"
echo $tbs_name > alter_source/b2c/tablespace

for t in $source_tbs;do
  echo "Check table $t schema";
  sqlplus -S ${uname}/${upwd} > run.tmp << EOF
SET PAGES 0 TRIMS ON ECHO OFF VERIFY OFF FEEDBACK OFF TERMOUT OFF LINESIZE 3000
SELECT 1  FROM  user_tab_columns where lower(table_name)=lower('$t');
EOF
  if test `wc -l < run.tmp` = 0;then
    echo "Table $t do not exist!skip"
    continue
  fi

  echo "Generate alter source table:$t";
  cat > alter_source/b2c/${t}.tab << EOF
def db=&1
def user=&2
def password=&3
def tbs=&4

alter table $t add (txn number); 
EOF

  echo "Generate view on source table:$t"
  cat > alter_source/b2c/$t.view << EOF
def db=&1
def user=&2
def password=&3
def tbs=&4
EOF

  sqlplus -S ${uname}/${upwd} >> alter_source/b2c/$t.view << EOF
SET PAGES 0 TRIMS ON ECHO OFF VERIFY OFF FEEDBACK OFF TERMOUT OFF LINESIZE 3000
select 'CREATE OR REPLACE FORCE VIEW sy\$${t} AS SELECT txn,'||listagg(column_name,',') within group(order by column_id) || ' FROM $t;' as ddl_view from user_tab_columns where lower(table_name)=lower('$t') and lower(column_name)<>'txn';
EOF
  
done

cd db/oracle
echo "Grant user"
./bin/alterUser.sh $uname $upwd $sid_name $tbs_name $tbs_path

cd bin

echo "Alter schema"
./alterSchema.sh ${uname}/${upwd}@${sid_name} ../../../alter_source/b2c/

cd ../../../

if test -f run.tmp;then
  echo "Clean tmp file"
  rm run.tmp
fi
