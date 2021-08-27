package com.cg.bookstore.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerServiceImpl customerServiceImpl;
	
	

	@Autowired
	private ObjectMapper objectMapper;
	
	private List<Customer> customerList;

	
	@Before
    void setUp() {
		customerList=new ArrayList<>();
		Address address= new Address("AC-22/9","kolkata","India","700567");
		LocalDate date= LocalDate.of(2012, 3, 21);
		this.customerList.add(new Customer("riya@gmail.com", "riya", "123@abc", address, "7631116004", date));
		
		
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	

	@Test
	void testCreateCustomer()  throws Exception
	{
	Address address= new Address("AC-22/9","kolkata","India","700567");
	LocalDate date= LocalDate.of(2019, 2, 19);
	Customer customer= new Customer("customer@gmail.com","customer", "pwd@1",address,"9876543213",date);
	
	given(customerServiceImpl.createCustomer(customer)).willAnswer((invocation)->invocation.getArgument(0));
	this.mockMvc.perform(post("/customer/createcustomer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(customer)))
			.andExpect(status().isOk());
			;		
	}
	@Test
	void testDeleteCustomer() throws Exception 
	{
		int custId=204;	
		Mockito.when(this.customerServiceImpl.deleteCustomer(custId))
				.thenReturn("Customer "+custId+" is deleted");
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deletecustomer/{id}",custId))
					.andExpect(status().isOk());	
	}

	@Test
	void testViewCustomer() throws Exception
	{
		int custId=204;
		ResponseEntity<Customer> customer=this.customerServiceImpl.viewCustomerById(custId);
		Mockito.when(this.customerServiceImpl.viewCustomerById(custId))
				.thenReturn(customer);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/viewcustomer/{id}",custId))
					.andExpect(status().isOk());
	}
	@Test
	void testViewAllCustomer() throws Exception
	{
		Mockito.when(this.customerServiceImpl.listCustomers())
				.thenReturn(customerList);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/viewallcustomers"))
					.andExpect(status().isOk());
	}

	@Test
	void testUpdateCustomer() throws Exception
	{
		Address address= new Address("EE-29/8","Mumbai","India","700897");
		LocalDate date= LocalDate.of(2012, 3, 21);
		Customer customer= new Customer("customer@gmail.com","customer", "pwd@",address,"9876543213",date);
		given(customerServiceImpl.updateCustomer(customer)).willAnswer((invocation)->invocation.getArgument(0));
		this.mockMvc.perform(put("/customer/updatecustomer",customer.getCustomerId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(customer)))
					.andExpect(status().isOk())
					;
	}
	
}