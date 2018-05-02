package com.example.switchyard.SY_Properties_Setter;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

@Service(MessageProcessor.class)
public class MessageProcessorBean implements MessageProcessor {

	@Reference CamelProcess camelProcess;
	
	@Override
	public String process(String arg) {
		System.out.println("### Bean processor gets:" + arg);
		String camelRet = camelProcess.process(arg);
		System.out.println("### Camel processor provided:" + camelRet);
		return camelRet;
	}

}
