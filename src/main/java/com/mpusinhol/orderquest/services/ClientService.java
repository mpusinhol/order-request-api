package com.mpusinhol.orderquest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpusinhol.orderquest.domain.Client;
import com.mpusinhol.orderquest.repositories.ClientRepository;
import com.mpusinhol.orderquest.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client findById(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	
	public List<Client> findAll() {
		List<Client> categories = clientRepository.findAll();
		
		return categories;
	}
}
