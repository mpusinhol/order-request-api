package com.mpusinhol.orderquest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mpusinhol.orderquest.domain.Client;
import com.mpusinhol.orderquest.repositories.ClientRepository;
import com.mpusinhol.orderquest.services.exception.DataIntegrityException;
import com.mpusinhol.orderquest.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client find(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	
	public List<Client> findAll() {
		List<Client> categories = clientRepository.findAll();
		
		return categories;
	}
	
	public Client insert(Client client) {
		client.setId(null);

		return clientRepository.save(client);
	}
	
	public Client update(Integer id, Client client) {
		Client newClient = find(id);
		
		updateData(newClient, client);

		return clientRepository.save(newClient);
	}
	
	public void delete(Integer id) {
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a client that has orders associated");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clientRepository.findAll(pageRequest);
	}
	
	private void updateData(Client newClient, Client oldClient) {
		newClient.setName(oldClient.getName());
		newClient.setEmail(oldClient.getEmail());
	}
}
