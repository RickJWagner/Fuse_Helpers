package org.wildfly.camel.examples.cdi;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
public interface HelloWorld{

	@WebMethod String getHelloWorldAsString(String name);

}