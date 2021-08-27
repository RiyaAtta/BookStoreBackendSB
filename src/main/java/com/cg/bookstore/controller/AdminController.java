package com.cg.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.service.IAdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;

@RestController
@RequestMapping("/admin")
@Api(value="Admin details",description="Operations pertaining to admin")
@CrossOrigin(origins = "http://localhost:4200") 
public class AdminController {

	@Autowired(required = true)
	IAdminService adminService;

	@PostMapping(value="/createadmin",produces="text/plain")
	@ApiOperation(value = "to create an admin", notes = "To add the details of the admin", response = Contact.class)
	ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
		adminService.createAdmin(admin);
		return ResponseEntity.ok("admin " + admin.getEmail() + " is created");
	}

	@GetMapping(value="/adminlogin/{email}/{password}",produces="text/plain")
	@ApiOperation(value = "admin login", notes = "To enter the email and password", response = Contact.class)
	public ResponseEntity<String> adminLogin(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		return adminService.validateLogin(email, password);
	}
	
}