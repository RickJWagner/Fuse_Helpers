package org.test.exceptions;

public class SystemException extends Exception{
	
	public SystemException(String str) {
		super(str);
	}

	public SystemException(String string, Throwable createSystemFault) {
		super(string);
	}

}
