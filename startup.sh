#!/bin/bash -e

if [[ ! -e ${ORADATA} ]] ; then
  mkdir -p ${ORADATA}/node01 ${ORADATA}/node02 ${ORADATA}/node03 ${ORADATA}/node04;
fi;

rm -rf m ${ORADATA}/ora_control*
orapwd file=orapwdorcl force=y
cp "init${ORACLE_SID}.ora" "${ORACLE_HOME}/dbs/"

sqlplus /nolog @create_db.sql
