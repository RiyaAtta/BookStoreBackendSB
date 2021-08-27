package com.cg.bookstore.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.service.ReviewServiceImpl;
import com.cg.bookstore.repository.IReviewRepository;

//@WebMvcTest(controllers= ReviewController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private IReviewRepository reviewRepo;

	/*
	 * @InjectMocks ReviewServiceImpl reviewService;
	 */

	@MockBean
	private ReviewServiceImpl reviewService;
	public List<Book> bookList;
	public List<Category> category;
	public List<Review> review;
	public List<Customer> customer;

	// Book book, Customer customer, String headLine, String comment, double rating,
	// LocalDate reviewOn
	@Before
	public void setUp() throws Exception {
		LocalDate d1 = LocalDate.of(1988, 5, 5);
		LocalDate d2 = LocalDate.now();
		this.customer.add(new Customer());
		byte[] anyInputStream = null ;
		this.bookList.add(new Book("JK", "HP", category.get(0), "Nice", "133545", 800, d1, anyInputStream));
		this.bookList.add(new Book("JK", "HP", category.get(0), "Nice", "133545", 800, d1, anyInputStream));
		this.review = new ArrayList<>();
		this.review.add(new Review(bookList.get(0), customer.get(0), "JK", "Very Nice", 4.5, d1));
		this.review.add(new Review(bookList.get(1), customer.get(0), "JK", "Good", 3.5, d1));
	}

	@Test
	public void testAddReviews() throws Exception {
		// fail("Not yet implemented");
		ResultActions s = this.mockMvc.perform(MockMvcRequestBuilders.post("/review/addreview").param("review", "r"))
				.andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("Review is valid"));
	}

	@Test
	public void testUpdateReview() throws Exception {
		// fail("Not yet implemented");
		this.mockMvc.perform(MockMvcRequestBuilders.put("/review/updatereview")
				// .accept(MediaType.APPLICATION_JSON)
				.content("{\"reviewId\":1\",\"rating\":3.5\"}").accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")).andExpect(status().isOk());
	}

	@Test
	public void testViewReview() throws Exception {
		int reviewId = 200;
		Review review = this.reviewService.viewReview(reviewId);
		Mockito.when(this.reviewService.viewReview(reviewId)).thenReturn(review);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/review/viewreview/{reviewId}", reviewId))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		int reviewId = 200;
		Mockito.when(this.reviewService.deleteReview(reviewId)).thenReturn("Review " + reviewId + " is deleted");
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deletereview/{reviewid}", reviewId))
				.andDo(print());
	}
}