package org.switchyard.quickstarts.camel.service;

public class CustomAction {
	
	public Object customActionMethod(Object obj) throws Exception {
		
		if (getRandomBoolean()) {
			System.out.println("### Failure happened!");
			throw new Exception("Random failure happened!");
		}else {
			System.out.println("### No failure happened this time");
		}
		
		return obj;
	}
	
	
	 private boolean getRandomBoolean() {
      return Math.random() < 0.5;
	 }

}
