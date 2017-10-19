This folder contains projects that demonstrate failover attempts for producer and consumer clients running outside the JBoss JVM.

In the development environment, tests were run using the enclosed domain.xml, with user 'rick' added to group 'guest'.


The POJO application works-- when the primary server is killed from the Domain console, both the producer and the consumer will failover to the second server.  The Camel and Spring applications (both of which use Spring JMS templates) do not failover.

Message counts can be obtained from the CLI with these:
/host=master/server=server-one/subsystem=messaging/hornetq-server=default/jms-queue=TestQ1/:read-resource(recursive=false,proxies=false,include-runtime=true,include-defaults=true)
/host=master/server=server-two/subsystem=messaging/hornetq-server=default/jms-queue=TestQ1/:read-resource(recursive=false,proxies=false,include-runtime=true,include-defaults=true)


