package com.cg.bookstore.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mockito.BDDMockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.service.BookServiceImpl;
import com.cg.bookstore.service.CategoryServiceImpl;
import com.cg.bookstore.service.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import oracle.jdbc.driver.BuildInfo;

//@WebMvcTest(controllers= BookController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	/*
	 * @InjectMocks BookServiceImpl bookService;
	 */

	@MockBean
	private BookServiceImpl bookService;
	@InjectMocks
	private BookController bookController;

	public List<Book> bookList;
	public List<Category> category;
	public Book b;

	@Before
	public void setUp() throws Exception {
		LocalDate d1 = LocalDate.of(1988, 5, 5);
		LocalDate d2 = LocalDate.now();
		this.bookList = new ArrayList<>();
		this.category = new ArrayList<>();
		this.category.add(new Category("Fiction"));
		this.category.get(0).setCategoryId(1);
		byte[] anyInputStream = null ;
		this.b = new Book("JK", "HP", category.get(0), "Nice", "9781594481772", 800, d1,anyInputStream);
		this.bookList.add(new Book( "JK", "HP", category.get(0), "Nice", "007462542X", 800, d1,anyInputStream));
		this.bookList.add(new Book( "JK", "HP", category.get(0), "Nice", "007462542X", 800, d1,anyInputStream));
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testgetAllBooks() throws Exception {
		// when(this.bookService.listAllBooks()).thenReturn(bookList);
		given(this.bookService.listAllBooks()).willReturn(bookList);
		this.mockMvc.perform(get("/book/getallbooks")).andExpect(status().isOk());
	}

	@Test
	public void testaddBook() throws Exception {
		given(this.bookService.listAllBooks()).willReturn(bookList);
		LocalDate d1 = LocalDate.of(1988, 5, 5);
		LocalDate d2 = LocalDate.now();
		this.category = new ArrayList<>();
		this.category.add(new Category("Fiction"));
		this.category.get(0).setCategoryId(1);
		byte[] anyInputStream = null ;
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/book/addbook")
						.content(
								asJsonString(new Book("JK", "HP", category.get(0), "Nice", "007462542X", 800, d1,anyInputStream)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("Book JK is added"));
	}

	@Test
	public void testsearchBookByID() throws Exception {
		// given(this.bookService.findBookById(201)).willReturn(new
		// ResponseEntity<>(b,HttpStatus.OK));
		Book resp = bookService.findBookById(101);
		Mockito.when(bookService.findBookById(101)).thenReturn(resp);
		mockMvc.perform(MockMvcRequestBuilders.get("/book/searchbookbyid/{id}", 101)).andExpect(status().isOk());
	}
	
	@Test
	public void testsearchBookByCategory() throws Exception {
		// given(this.bookService.findBookById(201)).willReturn(new
		// ResponseEntity<>(b,HttpStatus.OK));
		List<Book> resp = bookService.listBooksByCategory("fiction");
		Mockito.when(bookService.listBooksByCategory("fiction")).thenReturn(resp);
		mockMvc.perform(MockMvcRequestBuilders.get("/book/showbookbycategory/{category}", "fiction")).andExpect(status().isOk());
	}
	
	@Test
	public void testsearchBookByName() throws Exception {
		// given(this.bookService.findBookById(201)).willReturn(new
		// ResponseEntity<>(b,HttpStatus.OK));
		List<Book> resp = bookService.viewBookByName("HP");
		Mockito.when(bookService.viewBookByName("HP")).thenReturn(resp);
		mockMvc.perform(MockMvcRequestBuilders.get("/book/viewbookbyname/{name}", "HP")).andExpect(status().isOk());
	}
	

	@Test
	public void testDeleteBook() throws Exception {
		ResponseEntity<String> resp = bookService.deleteBook(100);
		Mockito.when(bookService.deleteBook(101)).thenReturn(resp);
		mockMvc.perform(MockMvcRequestBuilders.delete("/book/deletebook/{id}", 101)).andExpect(status().isOk());
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