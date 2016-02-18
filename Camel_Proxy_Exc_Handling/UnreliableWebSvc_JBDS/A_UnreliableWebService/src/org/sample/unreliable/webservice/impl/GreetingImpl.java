package org.sample.unreliable.webservice.impl;

import java.util.Random;

import javax.jws.WebService;

import org.sample.unreliable.webservice.Greeting;

@WebService(serviceName = "greeting", endpointInterface = "org.sample.unreliable.webservice.Greeting", targetNamespace = "http://cxf.examples.camel.wildfly.org/")
public class GreetingImpl implements Greeting {

	public java.lang.String greet(java.lang.String message,
			java.lang.String name) {

		String ret = null;
		int MAX = 3;
		int MIN = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
		if (randomNum > 1) {
			ret = message + "." + name;
		} else {
			int blowup = 0;
			int blowup2 = 0;
			int wontseethis = blowup / blowup2;
			//
			ret = "Shouldn't ever see this";
		}
		System.out.println("About to return " + ret);
		return ret;
	}
}