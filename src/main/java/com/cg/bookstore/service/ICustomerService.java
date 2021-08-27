package com.cg.bookstore.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;

@Service
public interface ICustomerService {

	public ResponseEntity<String> createCustomer(Customer customer);
	public List<Customer> listCustomers();
	public Customer updateCustomer(Customer c);
	public String deleteCustomer(Integer id);
	public ResponseEntity<Customer> viewCustomerById(int id);
	public Customer viewCustomer(Integer id);
	//public ResponseEntity<String> validateLogin(String email, String password);
	public Customer viewCustomerByEmail(String email);

	
}