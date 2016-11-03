package com.example.switchyard.rest_rules;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.switchyard.component.bean.Service;

@Service(OrderService.class)
public class OrderServiceImpl implements OrderService {
	private static long ORDER_ID = 0;
	private static ConcurrentMap<Long, Order> orderMap = new ConcurrentHashMap<Long, Order>();

	
	public OrderServiceImpl() {
		orderMap.put(ORDER_ID, new Order(ORDER_ID, "ProductA", 5L )); ORDER_ID++;
		orderMap.put(ORDER_ID, new Order(ORDER_ID, "ProductB", 5L )); ORDER_ID++;
		orderMap.put(ORDER_ID, new Order(ORDER_ID, "ProductC", 5L )); ORDER_ID++;
		orderMap.put(ORDER_ID, new Order(ORDER_ID, "ProductD", 5L )); ORDER_ID++;
		orderMap.put(ORDER_ID, new Order(ORDER_ID, "ProductE", 5L )); ORDER_ID++;
		orderMap.put(ORDER_ID, new Order(ORDER_ID, "ProductF", 5L )); ORDER_ID++;
	}


	@Override
	public Order getOrder(Long orderId) {
		Order anOrder = orderMap.get(orderId);
		return anOrder;
	}
}