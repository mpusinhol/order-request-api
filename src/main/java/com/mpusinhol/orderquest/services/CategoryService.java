package com.mpusinhol.orderquest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpusinhol.orderquest.domain.Category;
import com.mpusinhol.orderquest.repositories.CategoryRepository;
import com.mpusinhol.orderquest.services.exception.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category findById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}
	
	public List<Category> findAll() {
		List<Category> categories = categoryRepository.findAll();
		
		return categories;
	}
	
	public Category insert(Category category) {
		category.setId(null);

		return categoryRepository.save(category);
	}
}
