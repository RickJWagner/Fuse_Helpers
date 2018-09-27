package org.sample.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class HackedSAMLValidateInterceptor extends AbstractSoapInterceptor {

  public HackedSAMLValidateInterceptor() {
    super(Phase.PRE_PROTOCOL);
  }

  @Override
  public void handleMessage(SoapMessage soapMessage) throws Fault {
    try {
      System.out.println("### HackedSAMLValidateInterceptor::handleMessage() is running! ###");	
      HttpServletRequest httpServletRequest = getServletRequest(soapMessage);
      //httpServletRequest.login("abc@def", "no password required");      // This doesn't work.
      System.out.println("### Here is where the user wants security components to be used, but the above method doesn't work. ###");
      LoginContext lc = new LoginContext("some_domain", new JBossCallbackHandler(new SimplePrincipal("test"), "password"));      // This will work.
      lc.login();
      System.out.println("### The user DOES NOW HAVE security components available for use. ###");       
    }
    catch (Exception ex) {
      System.out.println("error at handle message");
      ex.printStackTrace();
      throw new Fault(ex);
    }
  }

  private HttpServletRequest getServletRequest(SoapMessage soapMessage) {
    if (soapMessage == null || soapMessage.getExchange() == null || soapMessage.getExchange().getInMessage() == null) {
      return null;
    }
    return (HttpServletRequest)soapMessage.getExchange().getInMessage().get("HTTP.REQUEST");
  }

}
