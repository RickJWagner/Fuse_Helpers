package org.switchyard.sample;
 
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
 
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
 
@Provider
@ServerInterceptor
public class SampleInterceptor implements PreProcessInterceptor {
    Logger logger = Logger.getLogger(SampleInterceptor.class);
 
    @Context
    HttpServletRequest servletRequest;
 
    public ServerResponse preProcess(HttpRequest request,
            ResourceMethod resourceMethod) throws Failure,
            WebApplicationException {
 
        String methodName = resourceMethod.getMethod().getName();
        logger.info("Receiving request from: " + servletRequest.getRemoteAddr());
        logger.info("Attempt to invoke method \"" + methodName + "\"");
        return null;
    }
}