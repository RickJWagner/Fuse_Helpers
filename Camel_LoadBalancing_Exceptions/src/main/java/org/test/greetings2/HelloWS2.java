package org.test.greetings2;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.test.exceptions.BusinessException;
import org.test.exceptions.FateDecider;
import org.test.exceptions.SystemException;

@WebService
public class HelloWS2 implements HelloService2 {


	@WebMethod
	@Override
	public String greet2(String message, String name) throws BusinessException, SystemException, Exception {
		System.out.println("org.test.greetings2.HelloWS2 invoked:" + name + "." + message);
		FateDecider.dangerousMethod(name);
		return name + " " + message;
	}
}
