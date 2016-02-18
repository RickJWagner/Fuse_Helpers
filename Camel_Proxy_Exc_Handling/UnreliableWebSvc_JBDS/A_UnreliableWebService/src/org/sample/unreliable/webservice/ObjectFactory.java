
package org.sample.unreliable.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sample.unreliable.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GreetResponse_QNAME = new QName("http://cxf.examples.camel.wildfly.org/", "greetResponse");
    private final static QName _Greet_QNAME = new QName("http://cxf.examples.camel.wildfly.org/", "greet");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sample.unreliable.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GreetResponse }
     * 
     */
    public GreetResponse createGreetResponse() {
        return new GreetResponse();
    }

    /**
     * Create an instance of {@link Greet }
     * 
     */
    public Greet createGreet() {
        return new Greet();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GreetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxf.examples.camel.wildfly.org/", name = "greetResponse")
    public JAXBElement<GreetResponse> createGreetResponse(GreetResponse value) {
        return new JAXBElement<GreetResponse>(_GreetResponse_QNAME, GreetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Greet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxf.examples.camel.wildfly.org/", name = "greet")
    public JAXBElement<Greet> createGreet(Greet value) {
        return new JAXBElement<Greet>(_Greet_QNAME, Greet.class, null, value);
    }

}
