package com.mpusinhol.orderquest;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mpusinhol.orderquest.domain.Address;
import com.mpusinhol.orderquest.domain.Category;
import com.mpusinhol.orderquest.domain.City;
import com.mpusinhol.orderquest.domain.Client;
import com.mpusinhol.orderquest.domain.Order;
import com.mpusinhol.orderquest.domain.Payment;
import com.mpusinhol.orderquest.domain.PaymentCreditCard;
import com.mpusinhol.orderquest.domain.PaymentSlip;
import com.mpusinhol.orderquest.domain.Product;
import com.mpusinhol.orderquest.domain.State;
import com.mpusinhol.orderquest.domain.enums.ClientType;
import com.mpusinhol.orderquest.domain.enums.PaymentState;
import com.mpusinhol.orderquest.repositories.AddressRepository;
import com.mpusinhol.orderquest.repositories.CategoryRepository;
import com.mpusinhol.orderquest.repositories.CityRepository;
import com.mpusinhol.orderquest.repositories.ClientRepository;
import com.mpusinhol.orderquest.repositories.OrderRepository;
import com.mpusinhol.orderquest.repositories.PaymentRepository;
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
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderRequestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Category category1 = new Category(null, "IT");
		Category category2 = new Category(null, "Office");
		Category category3 = new Category(null, "House");
		Category category4 = new Category(null, "Electronics");
		Category category5 = new Category(null, "Gardening");
		Category category6 = new Category(null, "Decoration");
		Category category7 = new Category(null, "Perfumery");
		
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
		
		Client client1 = new Client(null, "Mary Smith", "mary@gmail.com", "3245765433", ClientType.INDIVIDUALPERSON);
		
		client1.getPhones().addAll(Arrays.asList("111111111", "22222222"));
		
		Address address1 = new Address(null, "Flowers Street", 300, "Apt 303", "Gardens", "4234324324", client1, city1);
		Address address2 = new Address(null, "Simcoe Street", 200, "Apt 13", "Downtown", "34234323238", client1, city2);
		
		client1.getAddresses().addAll(Arrays.asList(address1, address2));
		
		Order order1 = new Order(null, null, address1, client1);
		Order order2 = new Order(null, null, address2, client1);
		
		Payment payment1 = new PaymentCreditCard(null, PaymentState.SETTLED, order1, 3);
		Payment payment2 = new PaymentSlip(null, PaymentState.PENDING, order2, dateFormatter.parse("02/02/2020"), null);
		
		order1.setPayment(payment1);
		order2.setPayment(payment2);
		
		client1.getOrders().addAll(Arrays.asList(order1, order2));
		
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5, category6, category7));
		productRepository.saveAll(Arrays.asList(product1, product2, product3));
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		clientRepository.save(client1);
		addressRepository.saveAll(Arrays.asList(address1, address2));
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
	}

}
