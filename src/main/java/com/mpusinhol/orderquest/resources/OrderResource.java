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
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Order order = orderService.findById(id);
		
		return ResponseEntity.ok(order);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Order> categories = orderService.findAll();
		
		return ResponseEntity.ok(categories);
	}
}
