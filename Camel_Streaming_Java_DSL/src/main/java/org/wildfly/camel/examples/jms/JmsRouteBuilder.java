/*
 * #%L
 * Wildfly Camel :: Example :: Camel JMS
 * %%
 * Copyright (C) 2013 - 2014 RedHat
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wildfly.camel.examples.jms;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;

@Startup
@ApplicationScoped
@ContextName("jms-cdi-context")
public class JmsRouteBuilder extends RouteBuilder {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    public void configure() throws Exception {
        /**
         * Configure the JMSComponent to use the connection factory
         * injected into this class
         */
        JmsComponent component = new JmsComponent();
        component.setConnectionFactory(connectionFactory);
        getContext().addComponent("jms", component);
        
        
        // Set up stream-caching.  If we don't use stream-caching, then we can only 'read' the streamed   
        // message once.  (So we couldn't log it, for instance.)
        // In this sample route we're not really using stream-caching, but demonstrate it here in case
        // you want to do more than one thing with the streamed message chunks.
        getContext().setStreamCaching(true);
        getContext().getStreamCachingStrategy().setSpoolDirectory("/home/rick/Junk/Camel");


        /**
         * This route reads files 
         * and places them onto a JMS queue.  
         * We break the file into one-line chunks so we can stream giant files through.  (Otherwise
         * we would have the whole thing in memory).
         */
        
        from("file:///home/rick/Junk/Camel/In")
        .log("Got a file!")
        .split(body().tokenize("\n")).streaming()
        .removeHeaders("*")
        .to("jms:queue:AQueue");
    }

}
