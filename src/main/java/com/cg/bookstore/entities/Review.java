package com.cg.bookstore.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="reviewtabledb")
@ApiModel(description="Review")
@JsonIgnoreProperties(ignoreUnknown = true) 
public class Review implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes="Review Id ")
	@Column(name="review_id")
	private int reviewId;

	@ApiModelProperty(notes="Book to be reviewed")
    @ManyToOne(fetch = FetchType.LAZY)
	private Book book;
    
    @ApiModelProperty(notes="Customer of the book")
	@OneToOne
	//@JsonIgnoreProperties
	@JoinColumn(name="cust_id")
    private Customer customer;
	
	@ApiModelProperty(notes="Headline of the book")
	@Column(name="headline")
	private String headLine;
	
	@ApiModelProperty(notes="Comment for the book")
	@Column(name="comments")
	private String comment;

	@ApiModelProperty(notes="Rating of the book")
	@Column(name="ratings")
	private double rating;
	
	@ApiModelProperty(notes="Date of review of book")
	@Column(name="date_reviewon")
	private LocalDate reviewOn;

	public Review(Book book, Customer customer, String headLine, String comment, double rating, LocalDate reviewOn) {
		super();
		this.book = book;
		this.customer = customer;
		this.headLine = headLine;
		this.comment = comment;
		this.rating = rating;
		this.reviewOn = reviewOn;
	}



	public Review() {
		super();
	}



	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	//@JsonBackReference
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getHeadLine() {
		return headLine;
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public LocalDate getReviewOn() {
		return reviewOn;
	}
	public void setReviewOn(LocalDate reviewOn) {
		this.reviewOn = reviewOn;
	}
}
