package org.wildfly.camel.examples.cxf.jaxws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class MyInterceptor extends AbstractPhaseInterceptor<Message> {

    public MyInterceptor() {
        super(Phase.RECEIVE);
    }
    
    public void handleMessage(Message message) {
        System.out.println("Handle Message in MyInterceptor");
        Properties myProps = loadProperties("my.properties");  // in modules/system/layers/fuse/org/mymodules/main/
        System.out.println("MyProperties " + myProps.getProperty("day"));
        System.out.println("MyInterceptor exits");
    }
    
    Properties loadProperties(String path) {
        Properties properties = new Properties();
        try {
        	InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        	properties.load(inStream);
        }catch(Exception e) {}
        return properties;
    }
        
}