package com.cg.bookstore.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Login;
import com.cg.bookstore.service.ILoginService;
 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;
 
@Api(value = "Login of customer", description = "Operations pertaining to customer login")
@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200") 
public class LoginController {
	@Autowired
	private ILoginService loginService;
 
//	@ApiOperation(value = "customer login", notes = "To login to his/her existing account by the customer", response = Contact.class)
//	@GetMapping("/login/{email}/{password}")
// 
//	private ResponseEntity<String> checkLogin(@Param("email") String email, @Param("password") String password) {
//		return loginService.validateLogin(email, password);
// 
//	}
		@ApiOperation(value = "customer login", notes = "To login to his/her existing account by the customer", response = Contact.class)
		@GetMapping(value="/login/{email}/{password}",produces="text/plain")
	 
		private ResponseEntity<String> checkLogin(@PathVariable("email")String email,@PathVariable("password")String password) {
			return loginService.validateLogin(email,password);
	 
		}
}