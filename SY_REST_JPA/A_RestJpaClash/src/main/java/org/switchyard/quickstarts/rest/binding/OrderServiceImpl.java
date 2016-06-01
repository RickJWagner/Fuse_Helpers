/*
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.switchyard.quickstarts.rest.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;
import org.switchyard.quickstarts.rest.binding.jpa.Message;
import org.switchyard.quickstarts.rest.binding.jpa.StoreService;


/**
 * An OrderService implementation.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
@Service(OrderService.class)
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private static final String SUCCESS = "SUCCESS";
    private static Integer orderNo = 0;
    private ConcurrentMap<Integer, Order> _orders = new ConcurrentHashMap<Integer, Order>();

    //@Inject @Reference
    //private Warehouse _warehouse;
    @Inject
    @Reference("StoreService")
    private StoreService _store;

    public OrderServiceImpl() {
    }

    public Order newOrder() {
        orderNo++;
        LOGGER.info("Creating new Order with no: " + orderNo);
        Order order = new Order(orderNo);
        _orders.put(orderNo, order);
        
        LOGGER.info("### About to store msg ###");
        Message msg = new Message("Me", "MeAgain");
        _store.storeGreeting(msg);
        LOGGER.info("### Stored msg ###");
        return order;
    }


}
