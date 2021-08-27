package com.cg.bookstore.exceptions;

public class CustomExceptionSchema{

	private String message;

	public CustomExceptionSchema(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	protected CustomExceptionSchema() {}

}