package org.sample;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2016-02-17T14:55:58.692-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://jaxws.examples.camel.wildfly.org/", name = "greeting")
@XmlSeeAlso({ObjectFactory.class})
public interface Greeting {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getGreeting", targetNamespace = "http://jaxws.examples.camel.wildfly.org/", className = "org.sample.GetGreeting")
    @WebMethod
    @ResponseWrapper(localName = "getGreetingResponse", targetNamespace = "http://jaxws.examples.camel.wildfly.org/", className = "org.sample.GetGreetingResponse")
    public java.lang.String getGreeting(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws MalformedURLException_Exception;
}
