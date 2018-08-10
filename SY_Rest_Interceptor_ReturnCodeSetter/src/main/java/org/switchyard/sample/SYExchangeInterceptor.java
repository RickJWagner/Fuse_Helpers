package org.switchyard.sample;

import java.util.Arrays;
import java.util.List;
 
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.switchyard.Exchange;
import org.switchyard.ExchangeInterceptor;
import org.switchyard.HandlerException;
import org.switchyard.Property;
 
/**
 * This is an example of an exchange interceptor which can be used to inject code
 * around targets during a message exchange.  
 */
@Named("UpdateStatus")
public class SYExchangeInterceptor implements ExchangeInterceptor {
 
    @Override
    public void before(String target, Exchange exchange) throws HandlerException {
        // Not interested in doing anything before the provider is invoked
    	if ((exchange.getProvider().getName().getLocalPart().equals("OrderService") &&
    			(exchange.getContract().getProviderOperation().getName().equalsIgnoreCase("getOrder"))) ){
    		System.out.println("### SYExchangeInterceptor::before fires! ###");
    		// Here we demonstrate we can find an HTTP header.  Be sure your mapper is configured for '.*' 
    		Property theHTTPHeader = exchange.getContext().getProperty("aspecialheader");
    		String headerValue = null;
    		if ((null != theHTTPHeader) && (null != theHTTPHeader.getValue())) {
    			headerValue = theHTTPHeader.getValue().toString();
    			System.out.println("### Found HTTP Header, value:" + headerValue);
    			// We'll decide in this case to look at the header value to cause an error
    			if ("badvalue".equals(headerValue)) {
    				throw new WebApplicationException(
    						Response
    								.status(Response.Status.FORBIDDEN)
    								.entity(new ErrorDto("403 - FORBIDDEN", "403 - FORBIDDEN")) // Because of JBossWeb Error Report
    								.type(MediaType.APPLICATION_JSON)
    								.build()
    				);
    			}
    		}
    	}else {
    		System.out.println("### SYExchangeInterceptor::before chooses not to fire ###");
    	}
    }
 
    @Override
    public void after(String target, Exchange exchange) throws HandlerException {
    	// we won't do anything here, we're just interested in incoming messages
    }
 
    @Override
    public List<String> getTargets() {
       return Arrays.asList(PROVIDER);
    }
    
    private class ErrorDto {

		private String code;
    	private String message;
    	
    	public ErrorDto(String string, String string2) {
			this.code = string;
			this.message = string2;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
    }
 
}