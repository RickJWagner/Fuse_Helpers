package org.test.exceptions;

public class BusinessException extends Exception {
	
	public BusinessException(String str) {
		super(str);
	}

	public BusinessException(String keineBerechtigungMeldung, Throwable createBusinessFault) {
		super(keineBerechtigungMeldung);
	}

}
