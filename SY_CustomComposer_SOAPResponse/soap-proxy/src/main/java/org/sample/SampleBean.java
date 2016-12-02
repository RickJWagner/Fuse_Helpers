package org.sample;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class SampleBean {
	public void doSomething(Exchange exchange) {
		System.out.println("Bean1 Received Exchange: " + exchange.getIn().getBody(String.class) + ", MIP: " + exchange.getPattern());
		
		// yuGdaB is 'BadGuy' reversed.  Just a sample of something we might decide to throw a 401 for.
		if(exchange.getIn().toString().contains("yuGdaB")){
			System.out.println("Bad Guy found");
			System.out.println(exchange.getIn().getBody().getClass().getName());
			System.out.println(exchange.getIn().toString());
			// By making the Body a String, we will cause an Exception later in the Composer.
			// We could make this a better type (with an embedded message) instead, this would be cleaner.
			exchange.getOut().setBody("SEND_401");
		}
		
	}
}
