package org.sample;

import java.util.Iterator;
import java.util.Set;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.ContextUtils;

public class SampleInterceptor extends AbstractSoapInterceptor {

    public SampleInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        System.out.println("### SampleInterceptor runs!###");
    }
}