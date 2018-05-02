package com.example.switchyard.SY_Properties_Setter;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageProcessorRoute extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		// TODO Auto-generated method stub
		from("switchyard://MessageProcessor")
		.log("Received message for 'MessageProcessor' : ${body}")
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("### In-line processor runs! ###");
                exchange.getIn().setHeader("myText", "text/html");    // Set this to whatever you want
            }})
		.to("switchyard://CamelProcess")
		.log("Camel route provides : ${body}");
	}

}
