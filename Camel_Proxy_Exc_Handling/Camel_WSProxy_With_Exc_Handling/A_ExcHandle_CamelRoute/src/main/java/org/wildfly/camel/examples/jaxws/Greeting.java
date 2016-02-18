package org.wildfly.camel.examples.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

public interface Greeting {

	
    public java.lang.String greet(
        @WebParam(name = "message", targetNamespace = "")
        java.lang.String message,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    );
    
}