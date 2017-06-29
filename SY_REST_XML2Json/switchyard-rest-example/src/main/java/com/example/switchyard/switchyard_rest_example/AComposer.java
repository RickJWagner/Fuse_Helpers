package com.example.switchyard.switchyard_rest_example;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.core.BaseClientResponse;
import org.switchyard.Exchange;
import org.switchyard.ExchangeState;
import org.switchyard.HandlerException;
import org.switchyard.Message;

import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyMessageComposer;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 *  A REST Composer that can selectively delete quotes from around particular elements in the JSON, keyed by the name of the element
 * 
 */



public class AComposer extends RESTEasyMessageComposer {

    private List<String> API_PRIMITIVES = Arrays.asList("{\"@Identifier\"", "\"SomeBoolean\"", "\"SomeInteger\"", "\"@Timestamp\"" );   

    /**
     * {@inheritDoc}
     */
    @Override
    public Message compose(RESTEasyBindingData source, Exchange exchange) throws Exception {
        return super.compose(source, exchange);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RESTEasyBindingData decompose(Exchange exchange, RESTEasyBindingData target) throws Exception {
        
        Object content = exchange.getMessage().getContent();

        target = super.decompose(exchange, target);

        Object [] objs = target.getParameters();

        StringBuilder fixedPayload = new StringBuilder();
        ArrayList <String> values = new ArrayList <String> (); 

        for (Object obj : objs){
            // find the response string's parts
            StringTokenizer st = new StringTokenizer(obj.toString(), ",");
            while (st.hasMoreElements()) {
                // find the first colon, this will be the divider between key and value
                String keyValue = st.nextElement().toString();
                String key = keyValue.substring(0,keyValue.indexOf(":"));
		        String value = keyValue.substring(keyValue.indexOf(":"), keyValue.length());
                if (API_PRIMITIVES.contains(key)  ){
                    // remove second and last chars (double quotes) 
                    value = value.charAt(0) + value.substring(2, value.length()-1);
                }
                fixedPayload.append(key);
                fixedPayload.append(value);
                fixedPayload.append(",\n");
		    }
        }
        // get rid of the last comma and space, there was no next keyValue pair
        String fixedTrimmedPayload = fixedPayload.toString().substring(0,fixedPayload.length() - 2);
        target.setParameters(new Object[] { fixedTrimmedPayload.toString() });
        return target;
    }
}
