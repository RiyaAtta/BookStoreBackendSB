package com.cg.bookstore.service;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.ICategoryRepository;
import com.cg.bookstore.service.CategoryServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest

class CategoryServiceImplTest {
	@Autowired 
	private CategoryServiceImpl categoryServiceImpl;
	
	@MockBean
	ICategoryRepository categoryServiceRepo;

	@Before
	static void setUpBeforeClass() throws Exception {
		System.out.println("Should print before all testcases!");
	}

	@After
	static void tearDownAfterClass() throws Exception {
		System.out.println("should execute after all the test case");
	}


	@Test
	void testAddCategory() {
		Category category= new Category("fiction");
		byte[] anyInputStream = null ;
		Book book= new Book("HP","JKR",category,"good","256-22",1999.00,LocalDate.of(1999, 12, 20), anyInputStream);
		when(categoryServiceRepo.save(category)).thenReturn(category);
		assertEquals("Category added!", categoryServiceImpl.addCategory("fiction"));
	}


	@Test
	void testRemoveCategory() {
		Category categoryobj=new Category();
		categoryobj.setCategoryName("fiction");
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Category deleted",HttpStatus.OK);
		when(categoryServiceRepo.findById(categoryobj.getCategoryId())).thenReturn(Optional.of(categoryobj));
		categoryServiceImpl.removeCategory(categoryobj.getCategoryId());
		verify(categoryServiceRepo).deleteById(categoryobj.getCategoryId());
	}

	@Test
	void testViewAllCategories() {
		List<Category> categoryList = new ArrayList();
		when(categoryServiceRepo.findAll()).thenReturn(categoryList);
		assertEquals(categoryList, categoryServiceImpl.viewAllCategories());
	}

}