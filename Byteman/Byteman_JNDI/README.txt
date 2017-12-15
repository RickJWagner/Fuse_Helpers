
1)  Make a setEnv.sh script to hold necessary env variables:
------------------
#!/bin/bash

# -- set the directory in which byteman has been installed
BYTEMAN_HOME=/home/rick/Tools/B/Byteman/byteman-download-3.0.6

BYTEMAN_JAR=/home/rick/Tools/B/Byteman/byteman-download-3.0.6/lib/byteman.jar

#   -- identify the samples helper jar
HELPER_JAR=/home/rick/workspace/Byteman/Byteman_JNDI/target/jndihelper-0.0.1-SNAPSHOT.jar

#   -- identify this script
SCRIPT=/home/rick/Support/01756558/Byteman/Byteman_Watch_JNDI/BYTEMAN_JNDI.BTM

TX_MGR_JAR=/home/rick/Tools/A/FSW6.0/Install/jboss-eap-6.1/modules/system/layers/base/javax/transaction/api/main/jboss-transaction-api_1.1_spec-1.0.1.Final-redhat-2.jar
TX_MGR_JAR2=/home/rick/Tools/A/FSW6.0/Install/jboss-eap-6.1/modules/system/layers/base/org/jboss/jts/integration/main/jbossjts-integration-4.17.7.Final-redhat-4.jar

export BYTEMAN_HOME
export BYTEMAN_JAR
export HELPER_JAR
export SCRIPT
export TX_MGR_JAR
export TX_MGR_JAR2

echo "BYTEMAN_HOME:"$BYTEMAN_HOME
echo "BYTEMAN_JAR:"$BYTEMAN_JAR
echo "HELPER_JAR:"$HELPER_JAR
echo "SCRIPT:"$SCRIPT
echo "TX_MGR_JAR:"$TX_MGR_JAR
echo "TX_MGR_JAR2:"$TX_MGR_JAR2
--------------------------------------------------------


2)  Add to standalone.sh to start Byteman:

------------------------------------

export JAVA_OPTS="${JAVA_OPTS} -javaagent:${BYTEMAN_JAR}=script:${SCRIPT},boot:${BYTEMAN_JAR},sys:${HELPER_JAR},sys:${TX_MGR_JAR},sys:${TX_MGR_JAR2}"
------------------------------------


3)  Use a Helper class if needed in Byteman rules.  Example:


# RULE JNDI_Dump_Rule 
# CLASS javax.naming.InitialContext
# METHOD list
# HELPER org.sample.byteman.JNDIHelper
# BIND ctx:InitialContext = $0;
# IF true
# DO printClues ();
# DO System.out.println("*** Byteman catches CTX! ***" + ctx.toString() );
# ENDRULE

RULE JNDI_Dumper 
CLASS org.switchyard.quickstarts.bean.service.JNDIDumperBean
METHOD void dumpJNDI()
IF true
DO System.out.println("*** Byteman catches JNDIDumperBean ***");
ENDRULE

RULE Print_TxMgr_Id 
CLASS com.arjuna.ats.jbossatx.jta.TransactionManagerDelegate
METHOD getTransaction
BIND txmgr:TransactionManagerDelegate = $0;
IF true
DO System.out.println("*** Byteman catches TxMgr! ***" + txmgr.toString() );
ENDRULE
