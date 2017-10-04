package org.wildfly.camel.examples.cdi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;


@Startup
@ApplicationScoped
@ContextName("cdi-context")
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	from("direct:start")
    	// Try to use HttpClient in the bean below
    	.beanRef("someBean","someMethod2")
         .log("Got:${body}")
         .setBody(constant("SUCCESS!"))    
    	.log("Done!");
    }

} 

