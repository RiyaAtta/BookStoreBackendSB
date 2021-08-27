package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.Bookt;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICategoryRepository;

@Service
public class BookServiceImpl implements IBookService {
	@Autowired
	private IBookRepository bookServiceRepo;

	@Autowired
	private ICategoryRepository categoryRepo;

	@Override
	public ResponseEntity<Book> createBook(@Valid Book book) {
		if(bookServiceRepo.findById(book.getBookId()).isPresent()) {
			throw new CustomException("Book present");
		}
		if(!book.getIsbn().matches("^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$")){
			throw new CustomException("ISBN Invalid");
		}
		if(book.getPrice()<=0) {
			throw new CustomException("Book Price must be greater than zero");
		}
		return categoryRepo.findByCategoryName(book.getCategory().getCategoryName()).map(category -> {
			book.setCategory(category);
			return new ResponseEntity<>(bookServiceRepo.save(book),HttpStatus.CREATED);
		}).orElse(new ResponseEntity<>(bookServiceRepo.save(book),HttpStatus.CREATED));
		//return new ResponseEntity<>(bookServiceRepo.save(book),HttpStatus.CREATED);
	}

	@Override
	public List<Book> listAllBooks() {
		return bookServiceRepo.findAll();
	}


	@Override
	public Book editBook(Bookt book) {
		// TODO Auto-generated method stub
		Book b=new Book();
		Optional<Book> findBookById = bookServiceRepo.findById(book.getBookId());
		if (findBookById.isPresent()) {
			Optional<Category> c = categoryRepo.findByCategoryName(book.getCategory().getCategoryName());
			book.setCategory(c.get());
			b.setTitle(book.getTitle());
			b.setAuthor(book.getAuthor());
			b.setBookId(book.getBookId());
			b.setCategory(book.getCategory());
			b.setDescription(book.getDescription());
			b.setPublishDate(book.getPublishDate());
			b.setIsbn(book.getIsbn());
			b.setPrice(book.getPrice());
			b.setDescription(book.getDescription());
			b.setBytes(findBookById.get().getBytes());
			return bookServiceRepo.save(b);
		} else
			throw new CustomException("Book with Id: " + book.getBookId() + " not exists!!");
	}

	@Override
	public List<Book> listBooksByCategory(String categoryName) {
		// TODO Auto-generated method stub
		Optional<Category> categoryPresentorNot = categoryRepo.findByCategoryName(categoryName);
		if (!categoryPresentorNot.isPresent()) {
			throw new CustomException("Book with +" + categoryName + " not found!");
		}
		return bookServiceRepo.findByCategory(categoryPresentorNot.get());
		//return null;
	}

	@Override
	public List<Book> viewBookByName(String bookName) {
		// TODO Auto-generated method stub
		if(bookServiceRepo.findByTitle(bookName).size()!=0) {
		return bookServiceRepo.findByTitle(bookName);
		}
		else {
			throw new CustomException("No book found");
		}
	}

	@Override
	public Book findBookById(Integer bookId) {
		// TODO Auto-generated method stub
		Optional<Book> findById = bookServiceRepo.findById(bookId);
		if (findById.isPresent()) {
			return bookServiceRepo.findByBookId(bookId);
		} else
			throw new CustomException("No record found with ID " + bookId);
	}

	@Override
	public ResponseEntity<String> deleteBook(Integer bookId) {
		Optional<Book> findBookById = bookServiceRepo.findById(bookId);
		if (findBookById.isPresent()) {
			bookServiceRepo.deleteById(bookId);
			// return "Book Deleted!!";
			return new ResponseEntity<>("Book deleted", HttpStatus.OK);
		} else
			throw new CustomException("Book not found for the entered BookID");
	}
	
	@Override
	public Book getBook(int bookId) {
		// TODO Auto-generated method stub
		return bookServiceRepo.findById(bookId).orElseThrow(() -> new CustomException("Book not found"));
	}

}
