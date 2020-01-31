package com.mpusinhol.orderquest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpusinhol.orderquest.domain.Category;
import com.mpusinhol.orderquest.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Category category = categoryService.findById(id);
		
		return ResponseEntity.ok(category);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Category> categories = categoryService.findAll();
		
		return ResponseEntity.ok(categories);
	}
}
