package org.test.exceptions;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class SystemExceptionHandler implements Processor {
	

	private static Logger LOG = null;
	
	public SystemExceptionHandler(Logger log) {
		LOG = log;
	}


	public void process(Exchange exchange) throws Exception {
		LOG.info("### SystemExceptionHandler:process() runs ###");
		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		LOG.info("### Exception: " + exception.getMessage() + "###");
		
		Message msg = exchange.getOut();
		msg.setFault(false);
		msg.setBody("A SystemException has occurred." + exception.getMessage());

	}

}
