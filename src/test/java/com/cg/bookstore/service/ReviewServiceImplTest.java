package com.cg.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.bookstore.entities.Review;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.IReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

class ReviewServiceImplTest {

	@Autowired
	private ReviewServiceImpl reviewServiceImpl;

	@MockBean
	IReviewRepository reviewServiceRepo;

	private int reviewId;

	@Before
	static void setUpBeforeClass() throws Exception {
		System.out.println("Should print before all testcases!");
	}



	@Test
	@DisplayName("Should add review")
	void testAddReview() throws Exception {
		System.out.println("Running testAddReview...");
		LocalDate d1 = LocalDate.of(2019, 2, 19);
		LocalDate d2 = LocalDate.of(20120, 4, 26);
		Category category = new Category("Science");
		byte[] anyInputStream = null ;
		Book book = new Book("JK", "HP", category, "Nice", "133545", 800.50, d1, anyInputStream);
		Address address = new Address("D-8/4", "kolkata", "India", "700062");
		Customer customer = new Customer("akash@gmail.com", "akash", "abc@123", address, "9843235145", d2);
		Review review = new Review(book, customer, "Abc", "Very Nice", 4.5, d1);
		when(reviewServiceRepo.save(review)).thenReturn(review);
		assertEquals(review, reviewServiceImpl.addReview(review));

	}

	@Test
	void testViewAllReviews() throws Exception {
		System.out.println("Running testViewAllReviews...");
		ArrayList<Review> reviewList = new ArrayList<Review>();
		given(reviewServiceRepo.findAll()).willReturn(reviewList);
		assertEquals(reviewServiceImpl.listAllReviews(), reviewList);
		verify(reviewServiceRepo).findAll();
	}

	@Test
	void testDeleteReviews() throws Exception {
		System.out.println("Running testDeleteReview...");
		int reviewId = 201;
		reviewServiceImpl.deleteReview(reviewId);
		verify(reviewServiceRepo).deleteById(reviewId);
	}

	@Test
	void testUpdateReview() throws Exception {
		System.out.println("Running testUpdateReview...");
		Review review = new Review();
		review.setReviewId(416);
		review.setRating(4.8);
		Review newReview = new Review();
		newReview.setReviewId(500);
		newReview.setRating(4.5);
		given(reviewServiceRepo.findById(review.getReviewId())).willReturn(Optional.of(newReview));
		reviewServiceImpl.updateReview(review);
		verify(reviewServiceRepo).save(review);
		verify(reviewServiceRepo).findById(review.getReviewId());
	}

	@Test
	void testViewReviewById() throws Exception {
		System.out.println("Running testViewReviewById...");
		Review review = new Review();
		review.setReviewId(284);
		when(reviewServiceRepo.findById(review.getReviewId())).thenReturn(Optional.of(review));
		reviewServiceImpl.viewReview(review.getReviewId());
		verify(reviewServiceRepo).findById(review.getReviewId());

	}

	@Test
	void testListAllReviewsByBook() throws Exception {
		System.out.println("Running testListAllReviewsByBook...");
	}

	@Test
	void testListAllCustomersByCustomer() throws Exception {
		System.out.println("Running testListAllCustomersByCustomer...");
	}
}