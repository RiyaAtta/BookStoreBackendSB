package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.dto.IBook;
import com.cg.bookstore.dto.IReview;
import com.cg.bookstore.entities.Review;

public interface IReviewService {

	public List<IReview> listAllReviews();
	public Review addReview(Review review);
	public Review updateReview(Review review);
	public List<IBook> listMostFavoredBooks();
	public List<IReview> listAllReviewsByCustomer(int custId);
	public Review viewReview(int reviewId);
	public List<IReview> listAllReviewsByBook(int bookId);
	public String deleteReview(int reviewId);
	public List<Review> listAllReviewss();
}