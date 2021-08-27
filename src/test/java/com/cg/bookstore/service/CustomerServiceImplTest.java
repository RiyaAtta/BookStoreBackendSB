package com.cg.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Login;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.ILoginRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

class CustomerServiceImplTest {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@MockBean
	ICustomerRepository customerServiceRepo;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Should print before all testcases!");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("should print before each test case");
		System.out.println("Instantitating the Customer Service");
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateCustomer() throws Exception
	{
		System.out.println("Running testCreateCustomer...");	
		Address address= new Address("AC-22/9","kolkata","India","700059");
		LocalDate date= LocalDate.of(2019, 2, 19);
		Customer customer= new Customer("riya@gmail.com", "riya", "123@abc", address, "7631116004", date);
		when(customerServiceRepo.save(customer)).thenReturn(customer);
		customerServiceImpl.createCustomer(customer);
		verify(customerServiceRepo).save(customer);
	}

	@Test
	void testListCustomers() throws Exception
	{
		System.out.println("Running testListCustomers...");	
		
		ArrayList<Customer> customer=new ArrayList();
		customer.add(new Customer());
		given(customerServiceRepo.findAll()).willReturn(customer);
		assertEquals(customerServiceImpl.listCustomers(),customer);
		verify(customerServiceRepo).findAll();
	}

		
	@Test
	void testViewCustomerById() throws Exception
	{
		System.out.println("Running testViewCustomerById...");	
		Customer customer=new Customer();
		customer.setCustomerId(244);
		when(customerServiceRepo.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		customerServiceImpl.viewCustomerById(customer.getCustomerId());
		verify(customerServiceRepo).findById(customer.getCustomerId());		
		
	}


	@Test
	void testViewCustomer() throws Exception
	{
		System.out.println("Running testViewCustomer...");
		Customer customer=new Customer();
		customer.setCustomerId(204);
		when(customerServiceRepo.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		customerServiceImpl.viewCustomer(customer.getCustomerId());
		verify(customerServiceRepo).findById(customer.getCustomerId());	
	}

}