
This example demonstrates use of JMS for a CXF/SOAP transport.

The request can be initiated at http://127.0.0.1:8080/camel-cxf-jaxws/cxf/

The queue can be monitored through CLI, if desired:
 /subsystem=messaging-activemq/server=default/jms-queue=OrdersQueue:read-resource(recursive=false,proxies=false,include-runtime=true,include-defaults=true)
