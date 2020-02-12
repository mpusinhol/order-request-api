package com.mpusinhol.orderquest.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mpusinhol.orderquest.domain.Category;
import com.mpusinhol.orderquest.domain.dto.CategoryDTO;
import com.mpusinhol.orderquest.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		Category category = categoryService.find(id);
		
		return ResponseEntity.ok(category);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> categories = categoryService.findAll();
		
		List<CategoryDTO> categoriesDTO = categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
		
		return ResponseEntity.ok(categoriesDTO);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Category> categories = categoryService.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CategoryDTO> categoriesDTO = categories.map(category -> new CategoryDTO(category));
		
		return ResponseEntity.ok(categoriesDTO);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO categoryDTO) {
		Category category = CategoryDTO.fromDTO(categoryDTO);
		category = categoryService.insert(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
		Category category = CategoryDTO.fromDTO(categoryDTO);
		category = categoryService.update(id, category);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
