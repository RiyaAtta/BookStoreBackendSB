package com.cg.bookstore.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.service.AdminServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private AdminServiceImpl adminService;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCreateAdmin() throws JsonProcessingException, Exception {
		Admin admin = new Admin(1, "shounak192@gmail.com", "12345");
		given(adminService.createAdmin(admin)).willAnswer((invocation) -> invocation.getArgument(0));
		this.mockMvc.perform(post("/admin/createadmin").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(admin))).andExpect(status().isOk());
	}

	@Test
	public void testAdminLogin() throws Exception {
		ResponseEntity<String> resp = adminService.validateLogin("shounak192@gmail.com", "12345");
		Mockito.when(adminService.validateLogin("shounak192@gmail.com", "12345")).thenReturn(resp);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/admin/adminlogin/{email}/{password}", "shounak192@gmai.com", "12345"))
				.andExpect(status().isOk());
	}

}
