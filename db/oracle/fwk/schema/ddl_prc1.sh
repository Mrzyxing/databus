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
        echo "Usage: $0 <username/password[@SID]> <SRC_TABLE>"
        exit 1
fi

DB=$1
SRC_TABLE=$2

sqlplus $DB << __EOF__ 
PROMPT creating sync_alert package

-- the purpose of this package is to allow to register to some alerts and get
-- notified when one is triggered
create or replace package sync_alert as

function registerSourceWithVersion(source in varchar, version in number) return number;
procedure unregisterAllSources;
function waitForEvent(maxWait in number) return varchar;
end sync_alert;
/
show errors

create or replace package body sync_alert as

is_registered boolean := FALSE;

-- registers a source with a version: after registration, all events occuring on this source
-- will be returned by waitForEvent
-- returns null if the source does not exists (source otherwise)
function registerSourceWithVersion(source in varchar, version in number) return number
as
  view_name varchar(30);
  bitnum number;
  source_name varchar(100);
  v_mode varchar(15);
begin
  if version > 0 then
    source_name := source || '_' || version;
  else
    source_name := source;
  end if;
  begin
   select open_mode into v_mode from db_mode;
   exception when others then
     v_mode :=null;
  end;
  begin
    select view_name into view_name from user_views where upper(view_name)=upper('sy\$' || source_name);
  exception when no_data_found then
    view_name := null;
  end;
  begin
    select bitnum into bitnum from sy\$sources where name=source;
  exception when no_data_found then
    bitnum := null;
  end; 
  IF v_mode='READ WRITE'  THEN
  IF not is_registered THEN
    begin
      dbms_alert.register('sy\$alert_${SRC_TABLE}');
    exception when others then
      null;
    end;
    is_registered := TRUE;
  END IF; 
  END IF;
  if (view_name is null or bitnum is null) then
    return null;
  end if;
  return bitnum;
end;

-- registers a source: after registration, all events occuring on this source
-- will be returned by waitForEvent
-- returns null if the source does not exists (source otherwise)
--- REM registersource function is removed  because it not used by any application code.

-- unregisters all sources. After this call, no more events are returned by
-- waitForEvent
procedure unregisterAllSources as
begin
  IF is_registered THEN
    dbms_alert.remove('sy\$alert_${SRC_TABLE}');
    is_registered := FALSE;
  END IF;
end;

-- wait for an even no longer than the time out (in seconds). Returns the message that
-- was associated to the event
function waitForEvent(maxWait in number) return varchar
as
   msg     varchar2(1800);
   status  number;
begin
   DBMS_ALERT.WAITONE('sy\$alert_${SRC_TABLE}', msg, status, maxWait);
   if status = 0 then
      return msg;
   end if;
   return null;
end;

end sync_alert;
/
show errors
__EOF__
