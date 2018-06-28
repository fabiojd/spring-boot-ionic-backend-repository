package com.fjdantas.cscmcourse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fjdantas.cscmcourse.domain.Category;
import com.fjdantas.cscmcourse.domain.City;
import com.fjdantas.cscmcourse.domain.Product;
import com.fjdantas.cscmcourse.domain.State;
import com.fjdantas.cscmcourse.repositories.CategoryRepository;
import com.fjdantas.cscmcourse.repositories.CityRepository;
import com.fjdantas.cscmcourse.repositories.ProductRepository;
import com.fjdantas.cscmcourse.repositories.StateRepository;

/*
 * class implements interface CommandLineRunner for objects instantiation in the initialization of the application
 */
@SpringBootApplication
public class CscmcourseApplication implements CommandLineRunner{

	/*
	 * dependencies for call operation of data access in the repository layer with auto instance by Spring with the Autowired annotation
	 */
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CscmcourseApplication.class, args);
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 * object instantiation method of the application
	 */
	@Override
	public void run(String... args) throws Exception {
		
		//instantiating of the objects of categories
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
				
		//instantiating of the objects of products
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		//adding the products elements into category list by association
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		//associating the products elements with your category list
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
			
		//saving a Category object list in the database with a auto list into saveAll method
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		//saving a Product object list in the database with a auto list into saveAll method
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//instantiating of the objects of States
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		//Instantiating of the objects of Cities
		City cit1 = new City(null, "Uberlândia", st1);
		City cit2 = new City(null, "São Paulo", st2);
		City cit3 = new City(null, "Campinas", st2);
		
		//associating the states elements with your cities list
		st1.getCities().addAll(Arrays.asList(cit1));
		st2.getCities().addAll(Arrays.asList(cit2, cit3));
		
		//saving a State object list in the database with a auto list into saveAll method
		stateRepository.saveAll(Arrays.asList(st1, st2));
		
		//saving a City object list in the database with a auto list into saveAll method
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		
	}
}
