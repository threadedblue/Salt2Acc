#!/usr/bin/env bash

. ./env.sh
printf "\ec"

cp -r conf $FED_HOME
cd $FED_HOME
echo `pwd`
echo "RTI_RID_FILE=$RTI_RID_FILE"
echo "CONFIG_FILE=$CONFIG_FILE"
java -D$LOG4J -D$IPV4 -jar $FEDERATE_NAME-0.0.1.jar $CONFIG_FILE
