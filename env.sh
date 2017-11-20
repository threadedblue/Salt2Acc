#!/usr/bin/env bash

export FEDERATE_NAME="ReceiveEMF"
export CURR_DIR=`pwd`
export SRC_DIR="$CURR_DIR/src/main/java"
export FED_HOME="$CURR_DIR/$FEDERATE_NAME"
export RTI_RID_FILE="conf/RTI.rid"
export LOG4J="log4j.configurationFile=conf/log4j2.xml"
export IPV4="java.net.preferIPv4Stack=true"
export CONFIG_FILE="$FED_HOME/conf/config.yml"
