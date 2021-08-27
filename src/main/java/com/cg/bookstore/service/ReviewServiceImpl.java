package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.IBook;
import com.cg.bookstore.dto.IReview;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IReviewRepository;

@Service
public class ReviewServiceImpl implements IReviewService {
	@Autowired
	private IBookRepository bookRepo;
	@Autowired
	private IReviewRepository reviewServiceRepo;

	@Override
	public Review addReview(Review review) {
		// TODO Auto-generated method stub
		if (bookRepo.findByBookId(review.getBook().getBookId()) != null)
			return reviewServiceRepo.save(review);
		else {
			throw new CustomException("Book is not present");
		}
	}

	@Override
	public List<IReview> listAllReviews() {
		// TODO Auto-generated method stub
		List<IReview> r = reviewServiceRepo.displayAll();
		return r;
	}
	@Override
	public List<Review> listAllReviewss(){
		List<Review> r=reviewServiceRepo.findAll();
		return r;
	}

	@Override
	public String deleteReview(int reviewId) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(reviewId);
		if (findReviewById.isPresent()) {
			reviewServiceRepo.deleteById(reviewId);
			return "Review Deleted!!";
		} else
			throw new CustomException("Review not found for the entered ReviewID");
	}

	@Override
	public Review viewReview(int reviewId) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(reviewId);
		if (findReviewById.isPresent()) {
			return findReviewById.get();
		} else
			throw new CustomException("Review not found for the entered ReviewID");
	}

	@Override
	public List<IReview> listAllReviewsByCustomer(int custId) {
		List<IReview> reviewsBytheCustomer = reviewServiceRepo.allReviewsByACustomer(custId);
		if (reviewsBytheCustomer != null) {
			return reviewsBytheCustomer;
		}
		throw new CustomException("No reviews by the customer");
	}

	@Override
	public Review updateReview(Review review) {
		// TODO Auto-generated method stub
		Optional<Review> findReviewById = reviewServiceRepo.findById(review.getReviewId());
		if (findReviewById.isPresent()) {
			reviewServiceRepo.save(review);
			return review;
		} else
			throw new CustomException("Review with Id: " + review.getReviewId() + " not exists!!");
	}

	@Override
	public List<IBook> listMostFavoredBooks() {
		// TODO Auto-generated method stub
		return reviewServiceRepo.listMostFavouredBooks();
	}

	@Override
	public List<IReview> listAllReviewsByBook(int bookId) {
		// TODO Auto-generated method stub
		List<IReview> reviewsByBook = reviewServiceRepo.allReviewsByBook(bookId);
		if (reviewsByBook != null) {
			return reviewsByBook;
		} else {
			throw new CustomException("Book Not Found");
		}
	}

}