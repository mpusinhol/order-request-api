package com.mpusinhol.orderquest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mpusinhol.orderquest.domain.Category;
import com.mpusinhol.orderquest.repositories.CategoryRepository;

@SpringBootApplication
public class OrderRequestApiApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderRequestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category1 = new Category(null, "IT");
		Category category2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(category1, category2));
	}

}
