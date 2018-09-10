package org.test.exceptions;

import java.util.Random;

public class FateDecider {
	
	public static String dangerousMethod(String input) throws Exception {
		
		// 
		
		
        if ("SE".equals(input) && somethingBroken()) {
        	throw new SystemException("SystemException");
        }
        if ("BE".equals(input)) {
        	throw new BusinessException("BusinessException");
        }
        if ("E".equals(input)) {
        	throw new Exception("Exception, should go to GenericExceptionHandler");
        }
        return input;
	}
	
	public static boolean somethingBroken() {
		  Random rand = new Random();
		  int  n = rand.nextInt(2) + 1;
		  if (1 == n) {
			  System.out.println("FateDecider.somethingBroken has decided something is broken");
			  return true;
		  }else {
			  System.out.println("FateDecider.somethingBroken has decided nothing is currently broken.");
			  return false;			  
		  }
	}
	
}
