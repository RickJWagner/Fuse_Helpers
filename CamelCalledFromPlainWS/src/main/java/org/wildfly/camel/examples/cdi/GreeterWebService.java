package org.wildfly.camel.examples.cdi;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;

@WebService
public class GreeterWebService {
	
	@Inject
	@ContextName("cdi-context")
	private CamelContext camelctx;
	

	@WebMethod
	public String greet(String name){
        ProducerTemplate producer = camelctx.createProducerTemplate();
        String result = producer.requestBody("direct:start", name, String.class);
		//return "Why hello " + name;
        return result;
	}
}
