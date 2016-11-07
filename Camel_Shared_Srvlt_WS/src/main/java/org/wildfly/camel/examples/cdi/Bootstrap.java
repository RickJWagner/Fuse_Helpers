package org.wildfly.camel.examples.cdi;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;


@Singleton
@Startup
public class Bootstrap {	
    
    private DefaultCamelContext camelCtx;

	@PostConstruct
	public void init() throws Exception {
		
		try {
			System.out.println("Init process begin in singleton bootstrap");
			
			camelCtx =  new DefaultCamelContext(getRegistry());

			System.out.println("Created a camel context with simple bean registry");
			
			this.camelCtx.addRoutes(new MyRouteBuilder());

			System.out.println("### Context starting... ###");
					
			this.camelCtx.start();

			System.out.println("### Context started ###");
		} catch (Exception e) {
			System.out.println("Exception in bootstrap during init " + e);
		}
	}

	@PreDestroy
	public void stop() throws Exception {
		this.camelCtx.stop();
	}
	
	private SimpleRegistry getRegistry() {
		
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("helloBean", new SomeBean());
		
		return registry;
	}


	public CamelContext getContext() {
		if (null == camelCtx){
			try{
				init();
			}catch(Exception e){
				System.out.println("Hey!  Context was null!");
			}
		}

		return camelCtx;
	}
}
