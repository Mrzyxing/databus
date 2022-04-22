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
usage() {
  echo "Usage: $0 username password dbname tablespace dbdir"
}

# Username
USER=$1

#Password
PASSWD=$2

#DBNAME
DBNAME=$3

#TableSpace
TBS=$4

#DBDIR
DBDIR=$5

# Default Log Dir
DEFAULT_LOG_DIR=logs

if [ "x$USER" = "x" ] || [ "x$PASSWD" = "x" ] || [ "x$DBNAME" = "x" ] || [ "x$TBS" = "x" ] || [ "x$DBDIR" = "x" ]
then
  usage
  exit 1
fi

TBS_LC=`echo $TBS | tr '[A-Z]' '[a-z]'`
TBS_UC=`echo $TBS | tr '[a-z]' '[A-Z]'`

echo "INFO: grant user"
sqlplus -S / as sysdba << __EOF__
grant create session, create table, create view, create sequence, create procedure, create trigger, create type, create job  to ${USER};
grant query rewrite to ${USER};
grant execute on dbms_alert to ${USER};
grant execute on sys.dbms_lock to ${USER};
grant select on sys.v_\$database to ${USER};
grant execute on sys.dbms_aq to ${USER};
grant execute on sys.dbms_aqadm to ${USER};
grant execute on sys.dbms_aqin to ${USER};
grant execute on sys.dbms_aq_bqview to ${USER};
show errors;
__EOF__

