
package org.wildfly.camel.file;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;

import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.io.StreamUtils;
import org.milyn.smooks.edi.unedifact.UNEdifactReaderConfigurator;
import org.xml.sax.SAXException;
import org.milyn.container.ExecutionContext;
import org.milyn.event.report.HtmlReportGenerator;
import org.milyn.payload.StringResult;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.camel.Exchange; 
import org.apache.camel.Processor;

@Startup
@ApplicationScoped
@ContextName("jms-file")
public class JmsRouteBuilder extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        /**
         * This route reads files placed within JBOSS_HOME/standalone/data/orders
         * and places them onto JMS queue 'ordersQueue' within the WildFly
         * internal HornetQ broker.
         */
        from("file:/home/rick/Junk/Camel/In")
        .process(new Processor() {
		public void process(Exchange exchange) throws Exception {
			String theXml = exchange.getIn().getBody(String.class);
                        String fileName = (String) exchange.getIn().getHeader("CamelFileAbsolutePath");
                        System.out.println("Filename:" + fileName);
			exchange.getOut().setBody("New Body");
			//Smooks smooks = new Smooks("smooks-config.xml");
			Smooks smooks = new Smooks("main2.xml");
			try {
			   StringResult result = new StringResult();
			   smooks.filterSource(new StreamSource(new FileInputStream(fileName)),null);
			   //System.out.println("Result:" + result.toString());

			} finally {
			    smooks.close();
			}
		}
		})
	.log("### Got a file! ###");
   }
}
