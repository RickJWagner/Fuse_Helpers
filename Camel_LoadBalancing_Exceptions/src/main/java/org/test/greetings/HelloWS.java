package org.test.greetings;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.test.exceptions.BusinessException;
import org.test.exceptions.FateDecider;
import org.test.exceptions.SystemException;

@WebService
public class HelloWS implements HelloService {

	@WebMethod
	public String greet (String message, String name) throws BusinessException, SystemException, Exception {
		System.out.println("org.test.greetings.HelloWS invoked:" + message + " " + name);
		FateDecider.dangerousMethod(name);
		return message + " " + name;
	}
}
