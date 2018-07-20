Based on 'Camel CDI Example'
-----------------

This example demonstrates failover using AMQ7 and Fuse 7.

Prerequisites
-------------

* Maven
* An application server with Red Hat Fuse installed

Running the example
-------------------

To run the example.

1. Start the application server in standalone mode:

    For Linux:

    ${JBOSS_HOME}/bin/standalone.sh -c standalone-full.xml

    For Windows:

    %JBOSS_HOME%\bin\standalone.bat -c standalone-full.xml

2. Build and deploy the project `mvn install -Pdeploy`

Browse to http://127.0.0.1:8080/example-camel-cdi/ProducerServlet?myarg=2a            <<< Note the argument, in this case '2a'
Then browse to http://127.0.0.1:8080/example-camel-cdi/ConsumerServlet

We should see the argument echoed back to us in the second trip.  This assures us that messaging is operational.

Using the provided AMQ configurations and standalone-xml file, we can see the following:

At least broker 1a running == success
Kill broker1a while broker1b is up == successful failover to backup (application works)
Kill broker1a and broker1b == no failover to broker 2a/2b.  Per the EAP doc, EAP connects to only 1 master/slave combination.
Kill broker1a and broker1b, leave them down, restart EAP == EAP will connect to 2a/2b on restart and will process normally.  
