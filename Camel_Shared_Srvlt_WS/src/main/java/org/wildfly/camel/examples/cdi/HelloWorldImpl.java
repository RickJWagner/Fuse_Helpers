package org.wildfly.camel.examples.cdi;

import javax.jws.WebService;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;



//Service Implementation
@WebService
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldAsString(String name) {
		
        // works great
        //return "Hello World JAX-WS " + name;

        CamelContext camelctx = new Bootstrap().getContext();
        ProducerTemplate producer = camelctx.createProducerTemplate();
        String result = producer.requestBody("direct:start", name, String.class);
        return result;        



        }

}