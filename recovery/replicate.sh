#!/bin/bash -e
LIGHT_GREEN="\033[1;32m"
NO_COLOR="\033[0m"
CONTROL_FILE_BACKUP="/u01/osp51/talldata/archive/standbycontrol.ctl"


recreate_redo_logs() 
{
  sqlplus / as sysdba <<EOF
    alter database add standby logfile group 3 '/u01/osp51/talldata/logs/s_redo01.log' size 100M;
    alter database add standby logfile group 4 '/u01/osp51/talldata/logs/s_redo02.log' size 100M;
    alter database add standby logfile group 5 '/u01/osp51/talldata/logs/s_redo03.log' size 100M;
EOF
  echo -e "${LIGHT_GREEN}[SUCCESS]${NO_COLOR} recreate redo-logs done"
}


recreate_controlfile()
{
  rm -f ${CONTROL_FILE_BACKUP}
  rman target sys/pass:s207687 auxiliary sys/pass@db174:1521/s1 <<EOF
    backup current controlfile for standby format "${CONTROL_FILE_BACKUP}";
EOF
  echo -e "${LIGHT_GREEN}[SUCCESS]${NO_COLOR} recreate control file: ${CONTROL_FILE_BACKUP} done"
}


update_tables()
{
  sqlplus stud/pass @add_row.sql <<EOF
    INSERT INTO TBL1 VALUES (1, 'New value!');
EOF
  echo -e "${LIGHT_GREEN}[SUCCESS]${NO_COLOR} Create new row in a TBL1 table done"
}


do_backup()
{
  rman target sys/pass:s207687 auxiliary sys/pass@db174:1521/s1 <<EOF
    run
      {
        allocate channel c1 device type disk format '/u01/osp51/talldata/archive/%u_%s_%p';
        backup database plus archivelog;
      }
EOF
  echo -e "${LIGHT_GREEN}[SUCCESS]${NO_COLOR} Backup files updated"
}

#rman target sys/pass:s207687 auxiliary sys/pass@db174:1521/s1


do_standby_recover()
{
  scp -r /u01/osp51/talldata/archive/* oracle@db174:/u01/osp51/talldata/archive/
  ssh oracle@db174 <<EOF
    bash
    source dbms/configure/env.sh
    sqlplus / as sysdba @recover
EOF
}

#recreate_controlfile
update_tables
do_backup
do_standby_recover
