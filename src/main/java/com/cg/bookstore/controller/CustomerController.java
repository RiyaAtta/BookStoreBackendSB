package com.cg.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.dto.OrderBookDto;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Order;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.service.ICustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;

@Api(value = "customer", description = "Operations pertaining to customer")
@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200") 
public class CustomerController {

	@Autowired(required = true)
	private ICustomerService customerService;
	@Autowired
	private ICustomerRepository custRepo;

	@ApiOperation(value = "to create a customer", notes = "To add details of the customer", response = Contact.class)
	@PostMapping(value="/createcustomer",produces="text/plain")
	ResponseEntity<String> createCustomer(@RequestBody Customer c) {
		// persisting the book
		return customerService.createCustomer(c);
	}

	@ApiOperation(value = "to delete a customer", notes = "To delete a customer", response = Contact.class)
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.ok("Customer " + id + " is deleted");
	}

	@ApiOperation(value = "to view a customer", notes = "To view a customer", response = Contact.class)
	@GetMapping("/viewcustomer/{id}")
	public Customer viewCustomer(@PathVariable("id") int id) {
		return customerService.viewCustomer(id);
	}
	@ApiOperation(value = "to view a customer by email", notes = "To view a customer", response = Contact.class)
	@GetMapping("/viewcustomerbyemail/{email}")
	public Customer viewCustomerByEmail(@PathVariable("email") String email) {
		return customerService.viewCustomerByEmail(email);
	}

	@ApiOperation(value = "view all the customers", notes = "To view all the customers", response = Contact.class)
	@GetMapping("/viewallcustomers")
	public List<Customer> viewAllCustomer() {
		return customerService.listCustomers();
	}

	@ApiOperation(value = "to update a customer", notes = "To update the details of the customer", response = Contact.class)
	@PutMapping("/updatecustomer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}
/*
 * extra functionality
 */

}