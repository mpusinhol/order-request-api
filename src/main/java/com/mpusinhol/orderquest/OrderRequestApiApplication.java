package com.mpusinhol.orderquest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mpusinhol.orderquest.domain.Category;
import com.mpusinhol.orderquest.domain.City;
import com.mpusinhol.orderquest.domain.Product;
import com.mpusinhol.orderquest.domain.State;
import com.mpusinhol.orderquest.repositories.CategoryRepository;
import com.mpusinhol.orderquest.repositories.CityRepository;
import com.mpusinhol.orderquest.repositories.ProductRepository;
import com.mpusinhol.orderquest.repositories.StateRepository;

@SpringBootApplication
public class OrderRequestApiApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderRequestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category1 = new Category(null, "IT");
		Category category2 = new Category(null, "Office");
		
		Product product1 = new Product(null, "Laptop", 2000.00);
		Product product2 = new Product(null, "Printer", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);
		
		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().add(product2);
		
		product1.getCategories().add(category1);
		product2.getCategories().addAll(Arrays.asList(category1, category2));
		product3.getCategories().add(category1);
		
		State state1 = new State(null, "São Paulo");
		State state2 = new State(null, "Minas Gerais");
		
		City city1 = new City(null, "Uberlândia", state2);
		City city2 = new City(null, "São Paulo", state1);
		City city3 = new City(null, "Campinas", state1);
		
		state1.getCitites().addAll(Arrays.asList(city2, city3));
		state2.getCitites().addAll(Arrays.asList(city1));
		
		categoryRepository.saveAll(Arrays.asList(category1, category2));
		productRepository.saveAll(Arrays.asList(product1, product2, product3));
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
	}

}
