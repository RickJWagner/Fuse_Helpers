package com.example.switchyard.sy_ejb;

import org.switchyard.component.bean.Service;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Remote(MsgLower.class)
@Stateless
@Service(MsgLower.class)
public class MsgLowerBean implements MsgLower {
	
	@Inject 
	SomeOtherSvc someOtherSvc;

	@Override
	public String toLower(String msg) {
		System.out.println("toLower (Remote EJB) runs.");
		String step1 = msg.toLowerCase();
		return someOtherSvc.someOtherSvc(step1);
	}

}
