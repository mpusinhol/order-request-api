package com.mpusinhol.orderquest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpusinhol.orderquest.domain.Order;
import com.mpusinhol.orderquest.repositories.OrderRepository;
import com.mpusinhol.orderquest.services.exception.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order findById(Integer id) {
		Optional<Order> order = orderRepository.findById(id);
		
		return order.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Order.class.getName()));
	}
	
	public List<Order> findAll() {
		List<Order> categories = orderRepository.findAll();
		
		return categories;
	}
}
