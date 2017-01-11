package org.sample;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class GreeterWS {
	
	@WebMethod
	public String greet(String who){
		System.out.println("### GreeterWS::greet() runs ###");
		return "Hi " + who;
	}

}
