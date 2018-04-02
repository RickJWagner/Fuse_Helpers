package org.switchyard.quickstarts.camel.service;

import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.http.composer.HttpBindingData;
import org.switchyard.component.http.composer.HttpContextMapper;
import org.switchyard.component.http.composer.HttpMessageComposer;
import org.switchyard.component.common.label.EndpointLabel;

public class CustomComposer extends HttpMessageComposer {
	
	@Override
	public Message compose(HttpBindingData source, Exchange exchange) throws Exception {
		final Message message = exchange.createMessage();

		getContextMapper().mapFrom(source, exchange.getContext(message));

		String body = source.getBodyAsString();
		System.out.println("### CustomComposer finds body as:" + body);
		
		if ((null == body) || (body.length() < 1) ) {
			body = "DefaultBody";
		}	
		message.setContent(body);
		return message;
	}

	
    @Override
    public HttpBindingData decompose(Exchange exchange, HttpBindingData target) throws Exception {
        Object content = exchange.getMessage().getContent();
        System.out.println("CustomComposer:decompose gets return type of :" + content.getClass().getName());
        System.out.println("CustomComposer:decompose gets:" + content.toString());
        if ((content instanceof String) && (content.equals(""))) {
            exchange.getContext().setProperty(HttpContextMapper.HTTP_RESPONSE_STATUS, 404).addLabels(new String[] { EndpointLabel.HTTP.label() });
        }
        if (content instanceof org.switchyard.HandlerException) {
        	System.out.println("Here is where we will find Exceptions that start in a Camel route used in SwitchYard");
            exchange.getContext().setProperty(HttpContextMapper.HTTP_RESPONSE_STATUS, 500).addLabels(new String[] { EndpointLabel.HTTP.label() });
            exchange.getMessage().setContent(new String ("Something bad has happened!"));
            
        }
        target = super.decompose(exchange, target);
        return target;
    }
}
