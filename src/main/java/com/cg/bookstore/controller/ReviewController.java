package com.cg.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Review;
import com.cg.bookstore.dto.IReview;
import com.cg.bookstore.dto.ReviewDto;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IReviewRepository;
import com.cg.bookstore.service.IReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;
@Api(value="Review of a Book",description="Operations pertaining to Review")
@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200") 
public class ReviewController {
	@Autowired
	private IReviewService reviewService;
	//*for testing .map
	@Autowired
	IReviewRepository reviewRepo;
	@Autowired
	IBookRepository bookRepo;
	@ApiOperation(value="add review",notes="To add a review",response=Contact.class)  
    @PostMapping("/addreview/{bookId}/review")
    public Review addReviewm(@PathVariable("bookId") int bookId,@Valid @RequestBody Review review) {  
    	 return bookRepo.findById(bookId).map(book->{
    		 review.setBook(book);
    		 return reviewRepo.save(review);
    	 }).orElseThrow(()->new CustomException("Book Not Present"));
    }
    @PostMapping("/addreview")
    public Review addReview(@Valid @RequestBody Review review) {
        // persisting the category
    	Review r= reviewService.addReview(review);
        return r;
    }
    
	@ApiOperation(value="to update the review",notes="To update the review of a book",response=Contact.class)
	@PutMapping("/updatereview")
	//@ExceptionHandler(ReviewNotFoundException.class)
	public Review updateReview(@RequestBody Review review){
		return reviewService.updateReview(review);
	}
	@ApiOperation(value="list all the reviews",notes="To list all the reviews",response=Contact.class)
    @GetMapping("/listallreviews")
    public List<Review> getAllReviews(){
    	return reviewService.listAllReviewss();
    }
	
	@ApiOperation(value="list all the reviews",notes="To list all the reviews",response=Contact.class)
    @GetMapping("/listallreview")
    public List<IReview> getAllReview(){
    	return reviewService.listAllReviews();
    }
	
	@ApiOperation(value="delete a review",notes="To delete a review",response=Contact.class)
	@DeleteMapping("/deletereview/{reviewId}")
	public String deleteReviewByReviewID(@PathVariable("reviewId") int reviewId) {

		return reviewService.deleteReview(reviewId);
	}
	@ApiOperation(value="view a review",notes="To view a review of the book",response=Contact.class)
	 @GetMapping("/viewreview/{reviewid}")
	// @ExceptionHandler(ReviewNotFoundException.class)
	    public Review viewReviewByReviewId(@PathVariable("reviewid") int reviewId) {
	    	return reviewService.viewReview(reviewId);
	    }
	@ApiOperation(value="list all the reviews of the book",notes="To view all the reviews of a book",response=Contact.class)
	 @GetMapping("/listallreviewsbybook")
	    public List<ReviewDto> getAllReviewByBookQuery(@RequestParam int bookId){
			 reviewService.listAllReviewsByBook(bookId);
			 List<ReviewDto> listd=new ArrayList<>();
		    	List<IReview> list=reviewService.listAllReviewsByBook(bookId);
		    	for(IReview i: list) {
		    		ReviewDto r=new ReviewDto();
		    		r.setFullName(i.getFullName());
		    		r.setRatings(i.getRatings());
		    		r.setReviewId(i.getReview_Id());
		    		r.setReviewOn(i.getDate_ReviewOn());
		    		r.setComments(i.getComments());
		    		r.setHeadLine(i.getHeadline());
		    		r.setTitle(i.getTitle());
		    		listd.add(r);
		    		
		    	}
		    	return listd;
		    }
	@ApiOperation(value="list all the reviews of a customer",notes="To list all the reviews of a customer",response=Contact.class)  
	 @GetMapping("/listallreviewsbycustomer")
	    public List<ReviewDto> getAllReviewByCustomer(@RequestParam int custId){
		 List<ReviewDto> listd=new ArrayList<>();
	    	List<IReview> list=reviewService.listAllReviewsByCustomer(custId);
	    	for(IReview i: list) {
	    		ReviewDto r=new ReviewDto();
	    		r.setRatings(i.getRatings());
	    		r.setReviewId(i.getReview_Id());
	    		r.setReviewOn(i.getDate_ReviewOn());
	    		r.setComments(i.getComments());
	    		r.setHeadLine(i.getHeadline());
	    		r.setTitle(i.getTitle());
	    		listd.add(r);
	    		
	    	}
	    	return listd;
	    }

}
