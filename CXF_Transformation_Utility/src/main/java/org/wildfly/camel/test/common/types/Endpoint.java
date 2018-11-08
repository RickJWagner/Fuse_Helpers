package org.wildfly.camel.test.common.types;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://wildfly.camel.test.cxf")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Endpoint {

   @WebMethod(operationName = "echoString", action = "urn:EchoString")
   String echo(@WebParam(name = "input", header = true, targetNamespace = "http://wildfly.camel.test.cxf") String input);
}