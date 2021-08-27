package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer>{
	public List<Book> findByTitle(String bookName);
	public Book findByBookId(int id);
	public List<Book> findByCategory(Category ob);



}