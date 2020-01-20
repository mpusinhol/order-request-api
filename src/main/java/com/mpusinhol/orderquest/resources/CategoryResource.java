package com.mpusinhol.orderquest.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpusinhol.orderquest.domain.Category;

@RestController
@RequestMapping(value="categories")
public class CategoryResource {
	
	@GetMapping
	public List<Category> listar() {
		Category category1 = new Category(1, "IT");
		Category category2 = new Category(2, "Office");
		
		List<Category> categories = new ArrayList();
		categories.addAll(Arrays.asList(category1, category2));

		return categories;
	}
}
