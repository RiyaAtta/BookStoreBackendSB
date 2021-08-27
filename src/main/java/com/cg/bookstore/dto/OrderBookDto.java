package com.cg.bookstore.dto;


import com.cg.bookstore.entities.Book;

public class OrderBookDto {

	private Book book;
    private Integer quantity;
    

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
