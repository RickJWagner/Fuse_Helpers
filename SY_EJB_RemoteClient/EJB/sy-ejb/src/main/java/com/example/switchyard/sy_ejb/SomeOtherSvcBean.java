package com.example.switchyard.sy_ejb;

import org.switchyard.component.bean.Service;

@Service(SomeOtherSvc.class)
public class SomeOtherSvcBean implements SomeOtherSvc {

	@Override
	public String someOtherSvc(String msg) {
		System.out.println("SomeOtherSvc in SY flow runs");
		return "SomeOtherSvc was here. " + msg;
	}
	
	

}
