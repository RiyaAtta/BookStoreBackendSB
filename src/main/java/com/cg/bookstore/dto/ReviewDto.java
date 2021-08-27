package com.cg.bookstore.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReviewDto {
	private String title;
	private int reviewId;
	private String comments;
	private String headLine;
	private LocalDate reviewOn;
	private int ratings;
	private String fullName;
	public ReviewDto(String title, int reviewId, String comments, String headLine, LocalDate reviewOn, int ratings,String fullName) {
		super();
		this.title = title;
		this.reviewId = reviewId;
		this.comments = comments;
		this.headLine = headLine;
		this.reviewOn = reviewOn;
		this.ratings = ratings;
		this.fullName=fullName;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setReviewId(Number reviewId) {
		this.reviewId = Integer.parseInt(reviewId.toString());
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ReviewDto() {
		super();
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	public void setReviewOn(Timestamp reviewOn) {
		this.reviewOn = reviewOn.toLocalDateTime().toLocalDate();
	}
	public void setRatings(Float ratings) {
		this.ratings = (int)(Math.round(ratings));
	}
	public String getTitle() {
		return title;
	}
	public int getReviewId() {
		return reviewId;
	}
	public String getComments() {
		return comments;
	}
	public String getHeadLine() {
		return headLine;
	}
	public LocalDate getReviewOn() {
		return reviewOn;
	}
	public int getRatings() {
		return ratings;
	}
}
