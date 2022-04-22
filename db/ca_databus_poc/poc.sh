#!/bin/sh

:<<COMMENT
Author: zyxing@trip.com on 2022/02/07
Desc:
  Run this script as oracle!

COMMENT

usage(){
  cat << USAGE
Usage: poc.sh sid_name tbs_path
 sid_name  oracle sid
 tbs_path  .dbf file absolute path
USAGE
}

if test $# != 2 ;then
  usage
  exit 1
fi

sid_name=$1
tbs_path=$2

mkdir -p $tbs_path

cd db/oracle

./bin/createUser.sh databus databus $sid_name tbs_test $tbs_path

cd bin

./createSchema.sh databus/databus@${sid_name} ../../../source_name/person/

