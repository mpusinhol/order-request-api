package com.mpusinhol.orderquest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpusinhol.orderquest.domain.Order;
import com.mpusinhol.orderquest.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id) {
		Order order = orderService.find(id);
		
		return ResponseEntity.ok(order);
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> categories = orderService.findAll();
		
		return ResponseEntity.ok(categories);
	}
}
