package com.cg.bookstore.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.repository.ICategoryRepository;
import com.cg.bookstore.service.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import oracle.jdbc.driver.json.tree.JsonpPrimitive.JsonpStringImpl;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	 @Autowired
	    private ObjectMapper objectMapper;
	
	@MockBean
	private CategoryServiceImpl categoryService;
	private List<Category> list;


	@Before
	public void setUp() throws Exception {
		 list=new ArrayList<>();
		this.list.add(new Category( "Fiction"));
		this.list.add(new Category( "Science"));
		this.list.add(new Category( "Action"));
		this.list.get(0).setCategoryId(1);
		this.list.get(1).setCategoryId(2);
		this.list.get(2).setCategoryId(3);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCategory() throws Exception{
		//fail("Not yet implemented");
		ResultActions s=this.mockMvc.perform(MockMvcRequestBuilders.post("/category/addcategory").param("categoryName", "Drama"))
        .andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=UTF-8"))
        .andExpect(content().string("Category Drama is valid"));

		
	}

	@Test
	public void testGetAllCategories() throws Exception {
		Mockito.when(this.categoryService.viewAllCategories())
		.thenReturn(list);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/category/viewallcategories"))
		.andExpect(status().isOk());

	}

	


		public static String asJsonString(final Object obj) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.registerModule(new JavaTimeModule());
				mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				return mapper.writeValueAsString(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
}
	  
	 