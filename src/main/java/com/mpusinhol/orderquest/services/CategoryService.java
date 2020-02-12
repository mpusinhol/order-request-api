package com.mpusinhol.orderquest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mpusinhol.orderquest.domain.Category;
import com.mpusinhol.orderquest.repositories.CategoryRepository;
import com.mpusinhol.orderquest.services.exception.DataIntegrityException;
import com.mpusinhol.orderquest.services.exception.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category find(Integer id) {
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
	
	public Category update(Integer id, Category category) {
		find(id);
		category.setId(id);

		return categoryRepository.save(category);
	}
	
	public void delete(Integer id) {
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a category that has products associated");
		}
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return categoryRepository.findAll(pageRequest);
	}
}
