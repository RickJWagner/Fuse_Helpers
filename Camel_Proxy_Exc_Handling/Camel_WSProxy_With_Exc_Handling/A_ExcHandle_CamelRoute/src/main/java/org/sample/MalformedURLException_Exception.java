
package org.sample;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2016-02-17T14:55:58.664-06:00
 * Generated source version: 2.7.14.redhat-1
 */

@WebFault(name = "MalformedURLException", targetNamespace = "http://jaxws.examples.camel.wildfly.org/")
public class MalformedURLException_Exception extends Exception {
    
    private org.sample.MalformedURLException malformedURLException;

    public MalformedURLException_Exception() {
        super();
    }
    
    public MalformedURLException_Exception(String message) {
        super(message);
    }
    
    public MalformedURLException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedURLException_Exception(String message, org.sample.MalformedURLException malformedURLException) {
        super(message);
        this.malformedURLException = malformedURLException;
    }

    public MalformedURLException_Exception(String message, org.sample.MalformedURLException malformedURLException, Throwable cause) {
        super(message, cause);
        this.malformedURLException = malformedURLException;
    }

    public org.sample.MalformedURLException getFaultInfo() {
        return this.malformedURLException;
    }
}
