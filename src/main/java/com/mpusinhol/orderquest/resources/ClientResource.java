package com.mpusinhol.orderquest.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpusinhol.orderquest.domain.Client;
import com.mpusinhol.orderquest.dto.ClientDTO;
import com.mpusinhol.orderquest.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Client> findById(@PathVariable Integer id) {
		Client client = clientService.find(id);
		
		return ResponseEntity.ok(client);
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> categories = clientService.findAll();
		
		List<ClientDTO> categoriesDTO = categories.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
		
		return ResponseEntity.ok(categoriesDTO);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Client> categories = clientService.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClientDTO> categoriesDTO = categories.map(client -> new ClientDTO(client));
		
		return ResponseEntity.ok(categoriesDTO);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO) {
		Client client = ClientDTO.fromDTO(clientDTO);
		client = clientService.update(id, client);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clientService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
