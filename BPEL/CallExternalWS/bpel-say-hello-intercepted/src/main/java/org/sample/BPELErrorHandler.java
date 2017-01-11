
package org.sample;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.switchyard.Exchange;
import org.switchyard.ExchangeInterceptor;
import org.switchyard.HandlerException;

@Named("BPEL Error handler")
public class BPELErrorHandler implements ExchangeInterceptor {

    @Override
    public void after(String target, org.switchyard.Exchange exchange)
            throws HandlerException {

        System.out.println("### The Interceptor is running!  From here, we can examine the data being exchanged and can alter it.  ###");

        /** 


        Object content = exchange.getMessage().getContent();
        if (content instanceof HandlerException
                && !(content instanceof org.switchyard.component.bpel.BPELFault)) {
            String newContents = "<integer xmlns=\"http://example.com/loan-approval/xsd/error-messages/\">-100</integer>";

        exchange.getContext().setProperty(Exchange.ROLLBACK_ON_FAULT, false);

        try {
        javax.xml.soap.SOAPFactory fact=javax.xml.soap.SOAPFactory.newInstance();
        javax.xml.soap.SOAPFault fault=fact.createFault("error", new javax.xml.namespace.QName("http://example.com/loan-approval/riskAssessment/", "loanProcessFault"));

        javax.xml.soap.Detail detail=fault.addDetail();

        javax.xml.soap.DetailEntry entry=detail.addDetailEntry(new javax.xml.namespace.QName("http://example.com/loan-approval/xsd/error-messages/", "integer"));
        entry.addTextNode("-100");

        exchange.getMessage().setContent(fault);

    

        } catch (Exception e) {
            e.printStackTrace();
        }
        **/


        
    }

    @Override
    public void before(String target, org.switchyard.Exchange exchange)
            throws HandlerException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<String> getTargets() {
        return Arrays.asList(PROVIDER);
    }

}
