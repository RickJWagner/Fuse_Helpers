/*
 * #%L
 * Wildfly Camel :: Example :: Camel JAX-WS
 * %%
 * Copyright (C) 2013 - 2014 RedHat
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wildfly.camel.examples.jaxws;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.bean.BeanInvocation;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Startup
@ApplicationScoped
@ContextName("jaxws-camel-context")
public class JaxwsRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		/**
		 * This route uses the camel-proxy to mimic a JAX-WS consumer endpoint.
		 *
		 * The direct:start endpoint is proxied in {@link GreetingServiceImpl}
		 * whenever any of the web service methods are invoked, it triggers the
		 * direct:start route to run.
		 *
		 * A simple processor implements the logic of each WebMethod and returns
		 * a response to the calling client.
		 */
		
		
		
		Predicate err_happened = (body().contains("faultcode"));

		from("direct:start")
				
			.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						BeanInvocation beanInvocation = exchange.getIn().getBody(BeanInvocation.class);
						String methodName = beanInvocation.getMethod().getName();
						System.out.println("### JaxwsRoute runs! ###");
						if (methodName.equals("greet")) {
							// etc. fill in as below
							String name = exchange.getIn()
									.getBody(String.class);
							exchange.getOut().setBody("Hello " + name);
						} else if (methodName.equals("greetWithMessage")) {
							String message = (String) beanInvocation.getArgs()[0];
							String name = (String) beanInvocation.getArgs()[1];
							String soapReq = makeSoapRequest(message, name);
							exchange.getOut().setBody(soapReq);
						} else {
							throw new IllegalStateException(
									"Unknown method invocation " + methodName);
						}
					}
				})
				
			.to("cxf://http://localhost:8080/A_UnreliableWebService/Greeting?serviceClass="
						+ Greeting.class.getName() + "&dataFormat=MESSAGE")
			
			// The body returned from the proxied service is a stream! Don't log it, or it will be consumed.
			// Converting it to a String is convenient
			.convertBodyTo(String.class).log("Body. ${body}")
			
			.log("###Body is now:${body}")
			
			.choice()
			.when(err_happened)
			.to("direct:err_route")
			.otherwise()
			
			// Extract the response from the backend service.  
			.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						String wsResp = exchange.getIn().getBody(String.class);
						System.out.println("wsResp:" + wsResp);
						int beginIndex = wsResp.indexOf("<return>");
						int endIndex = wsResp.indexOf("</return>");
						String resp = wsResp.substring(beginIndex + "<return>".length(), endIndex);
						exchange.getOut().setBody(resp);
					}
				});

		
		
		
		
		
		
		
		// Error handling route
		
		from("direct:err_route")
		.log("Error!${body}");
		
	}

	private String makeSoapRequest(String message, String name) {

		String line1 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cxf=\"http://cxf.examples.camel.wildfly.org/\">";
		String line2 = "<soapenv:Header/>";
		String line3 = "<soapenv:Body>";
		String line4 = "<cxf:greet>";
		String line5part1 = "<message>";
		String line5part2 = "</message>";
		String line6part1 = "<name>";
		String line6part2 = "</name>";
		String line7 = "</cxf:greet>";
		String line8 = "</soapenv:Body>";
		String line9 = "</soapenv:Envelope>";

		StringBuffer sb = new StringBuffer();
		sb.append(line1);
		sb.append(line2);
		sb.append(line3);
		sb.append(line4);
		sb.append(line5part1);
		sb.append(message);
		sb.append(line5part2);
		sb.append(line6part1);
		sb.append(name);
		sb.append(line6part2);
		sb.append(line7);
		sb.append(line8);
		sb.append(line9);
		String request = sb.toString();
		System.out.println("Formed request:" + request);
		return request;
	}




}
