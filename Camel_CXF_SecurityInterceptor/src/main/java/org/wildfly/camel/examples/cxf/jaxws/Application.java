/*
 * #%L
 * Wildfly Camel :: Example :: Camel CXF JAX-WS CDI XML
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
package org.wildfly.camel.examples.cxf.jaxws;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;

/**
 *
 * This example imports a Camel XML configuration file from the classpath using the {@code ImportResource} annotation.
 *
 * The imported Camel XML file configures a Camel context that references CDI beans declared in this class.
 *
 */

@ApplicationScoped
@ContextName("camel-jms-context")
@Named("cxf_cdi_java_app")
public class Application extends RouteBuilder {

    private CamelContext ctx = null; 

    @Override
    public void configure() throws Exception {

        ctx = this.getContext();    

        from("direct:start")
        .to("cxf:bean:cxfProducer");

        from("cxf:bean:cxfConsumer")
        .process("greetingsProcessor");

    }

    @Named("greetingsProcessor")
    @Produces
    public Processor createGreetingsProcessor() {
        return new GreetingsProcessor();
    }

    @Named("cxfConsumer")
    @Produces
    public CxfEndpoint createCxfConsumer() {
        CxfComponent cxfComponent = new CxfComponent(ctx);
        CxfEndpoint cxfFromEndpoint = new CxfEndpoint("http://localhost:8080/webservices/greeting-cdi-xml", cxfComponent);
        cxfFromEndpoint.setServiceClass(GreetingService.class);
        cxfFromEndpoint.getInInterceptors().add(new org.apache.cxf.interceptor.LoggingInInterceptor());
        cxfFromEndpoint.getInInterceptors().add(new org.sample.interceptors.HackedSAMLValidateInterceptor());
        return cxfFromEndpoint;
    }

    @Named("cxfProducer")
    @Produces
    public CxfEndpoint createCxfProducer() {
        CxfComponent cxfComponent = new CxfComponent(ctx);
        CxfEndpoint cxfToEndpoint = new CxfEndpoint("http://localhost:8080/webservices/greeting-cdi-xml", cxfComponent);
        cxfToEndpoint.getOutInterceptors().add(new org.apache.cxf.interceptor.LoggingOutInterceptor());
        cxfToEndpoint.setServiceClass(GreetingService.class);
        return cxfToEndpoint;
    }
}
