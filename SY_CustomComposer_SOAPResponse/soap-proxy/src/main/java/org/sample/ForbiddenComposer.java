package org.sample;

import javax.xml.namespace.QName;

import org.switchyard.Exchange;
import org.switchyard.component.soap.SOAPMessages;
import org.switchyard.component.soap.composer.SOAPBindingData;
import org.switchyard.component.soap.composer.SOAPMessageComposer;

public class ForbiddenComposer extends SOAPMessageComposer{
	
	@Override
	public SOAPBindingData decompose(Exchange exchange, SOAPBindingData target) throws Exception {
		SOAPBindingData data = target;
		try{
			data = super.decompose(exchange, target);
			// The 'BadGuy' will get an Exception because we changed the body type from 
			// com.sun.xml.messaging.saaj.soap.ver1_1.BodyElement1_1Impl to java.lang.String
			// If we wanted to be more thorough, we could have built an Element and sent it along instead
			// of a String, we would check the contents of the Element here instead of catching the Exception.
		}catch(Exception e){
			data.getSOAPMessage().getSOAPBody().addFault(new QName("401"), "That's forbidden for you!");
		}
        try {
            getContextMapper().mapTo(exchange.getContext(), data);
        } catch (Exception ex) {
            throw SOAPMessages.MESSAGES.failedToMapContextPropertiesToSOAPMessage(ex);
        }
        
        /**
        System.out.println("### ForbiddenComposer runs!  ###");
        System.out.println("###" + exchange.getMessage().toString() + "###");
		
        if (exchange.getMessage().toString().contains("SEND_401")){
        	System.out.println("### Will Send 401 ###");
        }
        **/
        
        return data;		
	}

}
