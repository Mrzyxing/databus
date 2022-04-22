#!/bin/sh
#
#
# Copyright 2013 LinkedIn Corp. All rights reserved
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
#
 
if [ "$#" -lt 2 ]
then
        echo "Usage: $0 <username/password[@SID]> <tables>"
	exit 1
fi

DB=$1

tables_uc=`echo $2|tr a-z A-Z`
echo "Clean table $tables_uc"

tables=${tables_uc//,/ }
views_name_arr=()
triggers_name_arr=()
tables_name_arr=()
for t in $tables
do
  tables_name_arr+=("'$t'")
  views_name_arr+=("'SY\$$t'")
  triggers_name_arr+=("'${t}_TRG'")
done

function join_by(){
  echo ${2// /$1}
}
views=`join_by , "${views_name_arr[*]}"`
triggers=`join_by , "${triggers_name_arr[*]}"`
tables=`join_by , "${tables_name_arr[*]}"`
echo "Follow views:$views and triggers:$triggers will clean!"

sqlplus -S $DB << __EOF__ | sqlplus -S $DB
SET PAGES 0 TRIMS ON ECHO OFF VERIFY OFF FEEDBACK OFF TERMOUT OFF LINESIZE 3000
SELECT 'set feedback off' from dual;

-- Disable any scheduled jobs so that no new runs are started while we are dropping
SELECT 'exec dbms_scheduler.disable('||chr(39)||job_name||chr(39)||', true);' 
    from user_scheduler_jobs WHERE JOB_NAME IN ('J_CALL_SIGNAL','J_COALESCE_LOG');

-- Stop any running jobs
-- First stop with "force" set to "false"
SELECT 'exec dbms_scheduler.stop_job('||chr(39)||job_name||chr(39)||','||'FALSE'||');' 
    from user_scheduler_jobs
    where state = 'RUNNING' AND JOB_NAME IN ('J_CALL_SIGNAL','J_COALESCE_LOG');


connect $DB
SELECT 'exec dbms_scheduler.stop_job('||chr(39)||job_name||chr(39)||','||'TRUE'||');' 
    from user_scheduler_jobs
    where state = 'RUNNING' AND JOB_NAME IN ('J_CALL_SIGNAL','J_COALESCE_LOG');
connect $DB

-- Now stop with "force" set to "true" - however this will typically fail
-- unless run as a higher-privileged user (such as sytem or sys)
SELECT 'exec dbms_scheduler.stop_job('||chr(39)||job_name||chr(39)||','||'TRUE'||');' 
    from user_scheduler_jobs
    where state = 'RUNNING' AND JOB_NAME IN ('J_CALL_SIGNAL','J_COALESCE_LOG');

SELECT 'exec dbms_scheduler.drop_job('||chr(39)||job_name||chr(39)||');' 
    from user_scheduler_jobs WHERE JOB_NAME IN ('J_CALL_SIGNAL','J_COALESCE_LOG');

SELECT 'exec dbms_scheduler.drop_program('||chr(39)||program_name||chr(39)||');'
    from user_scheduler_programs WHERE PROGRAM_NAME IN ('P_COALESCE_LOG');
 
__EOF__

echo "All job stopped!"

sqlplus -S $DB << __EOF__ | sqlplus -S $DB
SET PAGES 0 TRIMS ON ECHO OFF VERIFY OFF FEEDBACK OFF TERMOUT OFF LINESIZE 3000
SELECT 'set feedback off' from dual;

SELECT 'drop view ' || view_name || ';' FROM user_views WHERE view_name IN('DB_MODE',$views);

SELECT 'drop sequence ' || sequence_name || ';' FROM user_sequences where sequence_name IN('SY\$SCN_SEQ','VERCONTROL_SEQ');
   
SELECT 'drop trigger ' || trigger_name || ';' FROM user_triggers where trigger_name IN ($triggers);
   
SELECT distinct 'drop '||object_type||' '|| object_name || ';' FROM user_objects where object_type in ('FUNCTION','PROCEDURE') AND object_name IN('COMPILE_ALLOBJECTS');

SELECT  distinct 'drop package '|| object_name ||';'   FROM user_objects where object_type='PACKAGE' and object_name IN('SYNC_CORE','SYNC_ALERT');

SELECT 'drop table ' || table_name || ' cascade constraints;'   FROM user_all_tables WHERE NESTED='NO' and (IOT_TYPE='IOT' or IOT_TYPE is  null) AND table_name IN('SYNC_CORE_SETTINGS','PATCH_VERCONTROL','SY\$TXLOG','SY\$SOURCES');

__EOF__

echo "All databus resource cleaned!"

read -p "Drop column txn of table $tables.[y/n]" isdel
if test "x$isdel" == "xy";then
  sqlplus -S $DB << EOF | sqlplus -S $DB
SET PAGES 0 TRIMS ON ECHO OFF VERIFY OFF FEEDBACK OFF TERMOUT OFF LINESIZE 3000
SELECT 'set feedback off' from dual;
select 'alter table '||table_name||' drop column txn;' from user_tables where table_name in ($tables);
EOF
fi
