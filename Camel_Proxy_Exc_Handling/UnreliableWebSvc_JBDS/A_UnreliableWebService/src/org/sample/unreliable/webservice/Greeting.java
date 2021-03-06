package org.sample.unreliable.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2016-02-15T15:21:36.762-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://cxf.examples.camel.wildfly.org/", name = "greeting")
@XmlSeeAlso({ObjectFactory.class})
public interface Greeting {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "greet", targetNamespace = "http://cxf.examples.camel.wildfly.org/", className = "org.sample.unreliable.webservice.Greet")
    @WebMethod(action = "urn:greet")
    @ResponseWrapper(localName = "greetResponse", targetNamespace = "http://cxf.examples.camel.wildfly.org/", className = "org.sample.unreliable.webservice.GreetResponse")
    public java.lang.String greet(
        @WebParam(name = "message", targetNamespace = "")
        java.lang.String message,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    );
}
