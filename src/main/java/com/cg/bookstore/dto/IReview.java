package com.cg.bookstore.dto;

import java.sql.Timestamp;


public interface IReview {
	String getTitle();
	Number getReview_Id();
	String getComments();
	String getHeadline();
	Timestamp getDate_ReviewOn();
	Float getRatings();
	String getFullName();
}
