package org.test;

import java.util.logging.Logger;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.test.exceptions.BusinessException;
import org.test.exceptions.BusinessExceptionHandler;
import org.test.exceptions.GenericExceptionHandler;
import org.test.exceptions.SystemException;
import org.test.exceptions.SystemExceptionHandler;
import org.test.greetings.HelloService;
import org.test.greetings2.HelloService2;

@Startup
@ApplicationScoped
@ContextName("thecontext")
public class MyRouteBuilder extends RouteBuilder {


    
    private static final Logger LOG = Logger.getLogger(MyRouteBuilder.class.getName());
    
    // load balancer options
	private static final boolean INHERIT_ERROR_HANDLER = true;
	private static final boolean ROUND_ROBIN = true;
	private static final int MAXIMUM_FAILOVER_ATTEMPTS = 3;  // -1
	private static final boolean STICKY = false;
    
    @Named("cxfConsumer")
    @Produces
    public CxfEndpoint createCxfConsumer() {
        CxfComponent cxfComponent = new CxfComponent(this.getContext());
        CxfEndpoint cxfFromEndpoint = new CxfEndpoint("http://localhost:8080/camel-test-spring/HelloWS", cxfComponent);
        cxfFromEndpoint.setServiceClass(HelloService.class);
        return cxfFromEndpoint;
    }

    @Named("cxfConsumer2")
    @Produces
    public CxfEndpoint createCxfConsumer2() {
        CxfComponent cxfComponent = new CxfComponent(this.getContext());
        CxfEndpoint cxfFromEndpoint = new CxfEndpoint("http://localhost:8080/camel-test-spring/HelloWS2", cxfComponent);
        cxfFromEndpoint.setServiceClass(HelloService2.class);
        return cxfFromEndpoint;
    }
    
    
	@Override
    public void configure() throws Exception {
		
		/**
		 * Goal:
		 * Generic Exception:  Becomes either SystemException or BusinessException
		 * SystemException:  Might be able to recover.  End result could either be a good response or a stack trace.
		 * BusinessException:  Can't recover from a Business Exception.  User gets back a stack trace.
		 */
		    	
    	onException(Exception.class)//
        .process(new GenericExceptionHandler(LOG))
        .continued(true);

    
    onException(SystemException.class)
    .process(new SystemExceptionHandler(LOG));
     

    onException(BusinessException.class)//
        .process(new BusinessExceptionHandler(LOG));
       
    
    
    	from("direct:exceptiontest")
    	
    	// A first processor.  It gets called from a servlet at http://localhost:8080/camel-test-spring?name=someName
    	
        .process(new Processor() {
	   	  public void process(Exchange exchange) throws Exception {
	   		String passedArg = exchange.getIn().getBody().toString();
            LOG.info("First processor runs, passed name:" + passedArg);
            String message = "Hello";
            Object[] serviceParams = new Object[] { message, passedArg };
            exchange.getOut().setBody(serviceParams);
		} 
		}) 
               
       .loadBalance()//
       .failover(MAXIMUM_FAILOVER_ATTEMPTS, INHERIT_ERROR_HANDLER, ROUND_ROBIN, STICKY, SystemException.class)//
       .to("cxf:bean:cxfConsumer").to("cxf:bean:cxfConsumer2")
       .end()

       .process(new Processor() {
	   	  public void process(Exchange exchange) throws Exception {
           LOG.info("Final processor runs");
           if (exchange.getIn().getBody().toString().equals(GenericExceptionHandler.REMINDER_TO_SET_BUSINESS_EXCEPTION)) {
           	throw new BusinessException("BusinessException");
           }else {
           if (exchange.getIn().getBody().toString().equals(GenericExceptionHandler.REMINDER_TO_SET_SYSTEM_EXCEPTION)) {
               	throw new SystemException("SystemException");
           }
           }}
		}) 	
       
       .log("Finished. ${body}");
        
        
   }

	
    
}