package com.example.switchyard.sy_producers;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.switchyard.component.bean.Service;

import com.example.switchyard.sy_ejb.MsgLower;
import com.example.switchyard.sy_ejb.MsgLowerBean;

@Service(MsgAppender.class)
public class MsgAppenderBean implements MsgAppender {

	@Override
	public String appendGreeting(String msg) throws NamingException {
		System.out.println("MsgAppender runs.  Start with message:" + msg);
		String ejbModified = invokeStatelessBean(msg);	
		return "Hello " + ejbModified;
	}
	
	   private String invokeStatelessBean(String msg) throws NamingException {
	        // Look up the stub
		    MsgLower msglower = lookupLower();
		    return msglower.toLower(msg);
	    }	
	   
	    private  MsgLower lookupLower() throws NamingException {
	        final Hashtable jndiProperties = new Hashtable();
	        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        final Context context = new InitialContext(jndiProperties);
	        // The app name is the application name of the deployed EJBs. This is typically the ear name
	        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
	        // EJB deployment on the server.
	        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
	        final String appName = "";
	        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
	        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
	        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
	        // jboss-as-ejb-remote-app
	        final String moduleName = "sy-ejb";
	        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
	        // our EJB deployment, so this is an empty string
	        final String distinctName = "";
	        // The EJB name which by default is the simple class name of the bean implementation class
	        final String beanName = MsgLowerBean.class.getSimpleName();
	        // the remote view fully qualified class name
	        final String viewClassName = MsgLower.class.getName();
	        // let's do the lookup
	        String lookupString = new String("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName); 
	        return (MsgLower) context.lookup(lookupString);
	    }

}
