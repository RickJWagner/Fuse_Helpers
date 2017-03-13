/*
 * #%L
 * Wildfly Camel :: Testsuite
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
/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildfly.camel.examples.cdi;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.idempotent.jpa.JpaMessageIdRepository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.wildfly.extension.camel.CamelAware;





@Startup
@CamelAware
@ApplicationScoped
public class MyRouteBuilder extends RouteBuilder {

    @PersistenceUnit(unitName = "idempotentDb")
    private EntityManagerFactory entityManagerFactory; 

    @Resource(mappedName = "java:/jboss/UserTransaction")
    private UserTransaction userTransaction;
       

    @Override
    public void configure() throws Exception {

            final TransactionTemplate transactionTemplate = new TransactionTemplate();
            transactionTemplate.setTransactionManager(new JtaTransactionManager(userTransaction));
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        JpaMessageIdRepository repo = new JpaMessageIdRepository(entityManagerFactory, transactionTemplate, "myProcessor");

        from("file:/home/rick/Junk/Camel/In")
        .idempotentConsumer(header("CamelFileName"),repo)
        .log("### Got a file! ${body} ###")
        .bean("helloBean");
    }
}
