#!/bin/bash -e 

setup_target=$1
echo "Setup ${setup_target} in progress"

cp ${setup_target}/listener.ora ${ORACLE_HOME}/network/admin/listener.ora
cp ${setup_target}/tnsnames.ora ${ORACLE_HOME}/network/admin/tnsnames.ora

lsnrctl stop
lsnrctl start
