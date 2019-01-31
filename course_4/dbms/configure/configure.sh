#!/bin/bash -e

mkdir -p ${ORADATA}/node01 ${ORADATA}/node02 ${ORADATA}/node03 ${ORADATA}/node04;
mkdir -p ${ORADATA}/archive
mkdir -p ${ORADATA}/logs
mkdir -p ${ORADATA}/flash_recovery_area

rm -rf ${ORADATA}/ora_control*
orapwd file=orapw${ORACLE_SID} force=y

cp "init${ORACLE_SID}.ora" "${ORACLE_HOME}/dbs/"
cp "orapw${ORACLE_SID}" "${ORACLE_HOME}/dbs/"

sqlplus /nolog @create_db.sql
