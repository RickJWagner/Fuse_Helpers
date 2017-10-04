This folder contains 2 primary pieces:  An EAP REST Service that will be protected with SSL, and a Camel application that will use HttpClient to access it.

<Tips for the EAP portion>
--- start with  ./standalone.sh -c standalone-ha.xml -Djboss.socket.binding.port-offset=100      // So you get all necessary pieces, no port conflicts with Fuse
--- Invoke with http://localhost:8180/Protected_REST_App/rest/message/hi                         // Test the REST app.  Try HTTPS, too.
- Apply mutual authentication / TLS to the above service. (Add connector to standalone-ha.xml) 
--- Can prove the server with SOAPUI:
---- Load client keystore as seen at http://www.middlewareguru.com/mw/?p=472
---- SOAPUI now calls https://localhost:8543   /Protected_REST_App/rest/message/heyc, gets back correct response (also, EAP log w/SSL Debug on looks good)


<Tips for Fuse server>
(Start the server with args for the keystores)
./standalone.sh -Djavax.net.ssl.keyStore=/home/rick/Tools/A/Fuse6.2.1/Fuse_EAP/jboss-eap-6.4/standalone/configuration/client.jks -Djavax.net.ssl.keyStorePassword=Password1! -Djavax.net.ssl.trustStore=/home/rick/Tools/A/Fuse6.2.1/Fuse_EAP/jboss-eap-6.4/standalone/configuration/TheTrustStore.jks -Djavax.net.ssl.trustStorePassword=Password1!

The Fuse application is based on a quickstart.  When it is invoked, it will use the bean that uses HttpClient.  The service can be invoked via HTTP at http://localhost:8080/example-camel-cdi/?name=Kermit


--------- EAP mutual auth setup -----------------
https://developer.jboss.org/wiki/MutualAuthenticationOnJBoss720Final   // Follow these intructions to generate keystores for EAP and for Fuse.
https://girirajsharma.wordpress.com/2015/10/04/authentication-via-wildfly-mutual-ssl-two-way-configuration/



>>>>> Update for Java 8 <<<<<<<<<<<<<<<<<
SystemDefaultHttpClient is deprecated in the current 1.8.x JDK. The replacement is HttpClientBuilder. 
            HttpClient hcClient = HttpClientBuilder.create().build();

When on EAP, it may not use the default trust store and key store.  The solution:  
            HttpClient hcClient = HttpClientBuilder.create().useSystemProperties().build();

If you call the useSystemProperties() method before the  build() method, it forces the builder to use the system properties, including the default trust store and key store that have been configured in the VM. 
>>>>>> End update Java 8 <<<<<<<<<<<<<<<

