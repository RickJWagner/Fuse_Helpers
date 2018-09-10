package org.test.exceptions;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class GenericExceptionHandler implements Processor {
	
	 private static final String SECURITY_VIOLATION = "security violation";
	 private static final String KEINE_BERECHTIGUNG_MELDUNG = "Der Benutzer hat keine Berechtigung, diesen Service aufzurufen.";
	 
	 public static final String REMINDER_TO_SET_BUSINESS_EXCEPTION = "REMINDER_TO_SET_BUSINESS_EXCEPTION";
	 public static final String REMINDER_TO_SET_SYSTEM_EXCEPTION = "REMINDER_TO_SET_SYSTEM_EXCEPTION";

	 private final Logger LOG;

	  public GenericExceptionHandler(Logger logger) {
	    LOG = logger;
	  }
	  
	  @Override
	  public void process(Exchange exchange) throws Exception {
		  LOG.info("### GenericExceptionHandler:process() runs ###");
		  Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		  LOG.info("### Exception: " + exception.getMessage() + "###");

		  
		// We don't have real logic here, so the next section is commented out and replaced with random 
		// logic to throw either BusinessException or SystemException
		/**
	    String errorMessage = "Unerwartete " + exception.getClass().getName() + " aus dem Backend erkannt, Message: [" + exception.getMessage() + "].";
	    LOG.info(errorMessage);

	    String message = exception.getMessage();
	    if (message != null && message.toLowerCase().contains(SECURITY_VIOLATION)) {
	      BusinessException businessException = new BusinessException(KEINE_BERECHTIGUNG_MELDUNG, FaultTypeHelper.createBusinessFault(470, new java.util.ArrayList()));
	      throw businessException;
	    }

	    SystemException systemException = new SystemException("Technischer Fehler: " + exception.getMessage(), FaultTypeHelper.createSystemFault(500));
	    throw systemException;
	     */
		  
		  Random rand = new Random();
		  int  n = rand.nextInt(2) + 1;
		  System.out.println("GenericExceptionHandler calculates random value:" + n);
		  if (1 == n) {
			  exchange.getOut().setBody(REMINDER_TO_SET_BUSINESS_EXCEPTION);
		  }else {
			    exchange.getOut().setBody(REMINDER_TO_SET_SYSTEM_EXCEPTION);
		  }
	  }
	  



}
