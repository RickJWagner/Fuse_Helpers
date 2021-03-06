package org.sample;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2016-02-17T14:55:58.707-06:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebServiceClient(name = "GreetWSService", 
                  wsdlLocation = "file:/home/rick/Tools/Git_NotAll/Fuse6.2.1/Fuse6_Helpers/Fuse6.2.1/jboss-eap-6.4/bin/GreetWS.wsdl",
                  targetNamespace = "http://jaxws.examples.camel.wildfly.org/") 
public class GreetWSService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://jaxws.examples.camel.wildfly.org/", "GreetWSService");
    public final static QName GreetingPort = new QName("http://jaxws.examples.camel.wildfly.org/", "greetingPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/rick/Tools/Git_NotAll/Fuse6.2.1/Fuse6_Helpers/Fuse6.2.1/jboss-eap-6.4/bin/GreetWS.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(GreetWSService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/rick/Tools/Git_NotAll/Fuse6.2.1/Fuse6_Helpers/Fuse6.2.1/jboss-eap-6.4/bin/GreetWS.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public GreetWSService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public GreetWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GreetWSService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public GreetWSService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public GreetWSService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public GreetWSService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns Greeting
     */
    @WebEndpoint(name = "greetingPort")
    public Greeting getGreetingPort() {
        return super.getPort(GreetingPort, Greeting.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Greeting
     */
    @WebEndpoint(name = "greetingPort")
    public Greeting getGreetingPort(WebServiceFeature... features) {
        return super.getPort(GreetingPort, Greeting.class, features);
    }

}
