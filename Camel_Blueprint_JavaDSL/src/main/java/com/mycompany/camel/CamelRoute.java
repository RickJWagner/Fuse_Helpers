package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("timer://foo2?period=10s")
			.log("### Hey! Camel route runs!  ###");
	}

}
