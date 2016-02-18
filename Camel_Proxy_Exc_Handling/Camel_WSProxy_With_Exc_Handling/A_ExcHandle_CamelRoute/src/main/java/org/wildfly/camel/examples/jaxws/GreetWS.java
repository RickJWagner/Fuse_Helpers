package org.wildfly.camel.examples.jaxws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@WebService(name = "greeting")
public class GreetWS {
	
	@WebMethod
	public String getGreeting(String name, String message) throws MalformedURLException{
		
	       /**
         * Create a JAX-WS client to invoke the greeting web service
         */
        URL wsdlLocation = new URL("http://localhost:8080/example-camel-jaxws/greeting?wsdl");
        QName serviceName = new QName("http://jaxws.examples.camel.wildfly.org/", "greeting");
        Service service = Service.create(wsdlLocation, serviceName);
        GreetingService greetingService = service.getPort(GreetingService.class);

        /**
         * Invoke the web service methods
         */
        String greeting;
        if(message != null && !message.isEmpty() && name != null && !name.isEmpty()) {
            greeting = greetingService.greetWithMessage(message, name);
        } else if((message == null || message.isEmpty()) && name != null && !name.isEmpty()) {
            greeting = greetingService.greet(name);
        } else {
            greeting = "Hello unknown";
        }		
		
        return greeting;
	}

}
