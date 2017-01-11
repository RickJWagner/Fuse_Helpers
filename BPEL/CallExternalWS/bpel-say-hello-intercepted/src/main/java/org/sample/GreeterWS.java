package org.sample;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.ws.api.annotation.WebContext;

@WebService
@WebContext( contextRoot = "/ws" ) 
public class GreeterWS {
	
	@WebMethod
	public String greet(String who){
		System.out.println("### GreeterWS::greet() runs ###");
		return "Hi " + who;
	}

}
