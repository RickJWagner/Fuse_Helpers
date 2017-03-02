/*
 * #%L
 * Wildfly Camel :: Example :: Camel JPA
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
package org.wildfly.camel.examples.jpa;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wildfly.camel.examples.jpa.model.Customer;

@SuppressWarnings("serial")
@WebServlet(name = "HttpServiceServlet2", urlPatterns = { "/customer/*" }, loadOnStartup = 1)
public class SimpleServlet2 extends HttpServlet {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("### About to find first customer ###");
        List <Customer> singleCustomerList = customerRepository.getSingleCustomerList();      
        System.out.println("### Done with find all customers ###");

        request.setAttribute("customers", singleCustomerList);
        request.getRequestDispatcher("customers.jsp").forward(request, response);
    }
}
