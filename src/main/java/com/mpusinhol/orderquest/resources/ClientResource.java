package com.mpusinhol.orderquest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpusinhol.orderquest.domain.Client;
import com.mpusinhol.orderquest.services.ClientService;

@RestController
@RequestMapping(value="clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Client client = clientService.findById(id);
		
		return ResponseEntity.ok(client);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Client> categories = clientService.findAll();
		
		return ResponseEntity.ok(categories);
	}
}
