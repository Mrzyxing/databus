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
if [ "$#" -lt 1 ]  
then    
        echo "Usage: $0 <username/password[@SID]>"
        exit 1                                                                                          
fi                                 
                      
DB=$1 

sqlplus -S $DB << __EOF__ 

BEGIN
DBMS_SCHEDULER.CREATE_PROGRAM(
program_name=>'P_COALESCE_LOG',
program_action=>'Begin
sync_core.coalesce_log;
end;',
program_type=>'PLSQL_BLOCK',
number_of_arguments=>0,
comments=>'New program used to update scn',
enabled=>TRUE);
DBMS_SCHEDULER.CREATE_JOB(
JOB_NAME => 'J_COALESCE_LOG',
PROGRAM_NAME => 'P_COALESCE_LOG',
REPEAT_INTERVAL  => 'FREQ=SECONDLY;INTERVAL=2',
START_DATE => systimestamp at time zone 'US/Pacific',
COMMENTS => 'this will update the scn on sy$txlog',
AUTO_DROP => FALSE,
ENABLED => FALSE);
DBMS_SCHEDULER.SET_ATTRIBUTE(NAME=>'J_COALESCE_LOG', attribute => 'restartable', value=>TRUE);
DBMS_SCHEDULER.SET_ATTRIBUTE( NAME =>'J_COALESCE_LOG', attribute =>'logging_level', value => DBMS_SCHEDULER.LOGGING_OFF);
DBMS_SCHEDULER.ENABLE('J_COALESCE_LOG');
END;
/

BEGIN
dbms_scheduler.create_job(
job_name => 'J_CALL_SIGNAL',
job_type => 'PLSQL_BLOCK',
job_action => 'begin
   sync_core.unconditional_signal_beep;
end;',
repeat_interval => 'FREQ=SECONDLY',
start_date =>  systimestamp at time zone 'US/Pacific',
job_class => 'DEFAULT_JOB_CLASS',
comments => 'Call sync_core.unconditional_signal_beep to signal that databus events MAY be available',
auto_drop => FALSE,
enabled => FALSE);
sys.dbms_scheduler.set_attribute( name => 'J_CALL_SIGNAL', attribute => 'job_priority', value => 1);
sys.dbms_scheduler.set_attribute( name => 'J_CALL_SIGNAL', attribute => 'logging_level', value => DBMS_SCHEDULER.LOGGING_OFF);
sys.dbms_scheduler.set_attribute( name => 'J_CALL_SIGNAL', attribute => 'job_weight', value => 1);
sys.dbms_scheduler.disable( 'J_CALL_SIGNAL' );
END;
/

show errors
__EOF__

