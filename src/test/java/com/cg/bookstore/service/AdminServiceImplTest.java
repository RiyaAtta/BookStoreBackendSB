package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.repository.IAdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {
	
	@Autowired
	private IAdminService adminService;
	
	@MockBean
	private IAdminRepository adminRepository;
	
	@Test
	public void createAdminTest() {
		Admin admin= new Admin(1,"s@gmail.com", "123@456");
		when(adminRepository.save(admin)).thenReturn(admin);
		assertEquals(admin, adminService.createAdmin(admin));
	}
	

	@Test
	public void validateLoginTest() {
		Admin admin= new Admin(1,"s@gmail.com", "123@456");
		ResponseEntity<String> responseEntity= new ResponseEntity<>("User Valid",HttpStatus.OK);
		when(adminRepository.findByEmail(admin.getEmail())).thenReturn(admin);
		assertEquals(responseEntity, adminService.validateLogin(admin.getEmail(),admin.getPassword()));
		
		
	}

}