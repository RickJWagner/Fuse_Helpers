package org.test.exceptions;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class BusinessExceptionHandler implements Processor{
	
	private static Logger LOG = null;

	public BusinessExceptionHandler(Logger log) {
		LOG = log;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOG.info("### BusinessExceptionHandler:process() runs ###");
		
		Message msg = exchange.getOut();
		msg.setFault(false);
		msg.setBody("Fault has not been set!");
	}

}
