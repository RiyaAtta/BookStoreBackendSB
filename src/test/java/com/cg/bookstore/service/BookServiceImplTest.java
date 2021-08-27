package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

	@Autowired
	private BookServiceImpl bookService;

	private List<Book> bookList;
	Category category = null;

	@MockBean
	private IBookRepository bookRepo;

	@MockBean
	private ICategoryRepository categoryRepo;

	@Before
	public void setUp() throws Exception {
		category = new Category("fiction");
		bookList = new ArrayList<>();
		byte[] anyInputStream = null ;
		this.bookList.add(new Book("HP", "JKR", category, "good", "9781594481772", 1999.00, LocalDate.of(1999, 12, 20),
				anyInputStream));
		this.bookList.add(new Book("AGERS", "SL", category, "good", "9781594481772", 1999.00, LocalDate.of(1999, 12, 20),
				anyInputStream));
		this.bookList.add(new Book("POK", "JH", category, "good", "9781594481772", 1999.00, LocalDate.of(1999, 12, 20),
				anyInputStream));
	}

	@Test
	public void testCreateBook() {
		category = new Category("fiction");
		byte[] anyInputStream = null ;
		Book book = new Book("HP", "JKR", category, "good", "9781594481772", 1999.00, LocalDate.of(1999, 12, 20),
				anyInputStream);
		when(bookRepo.save(book)).thenReturn(book);
		assertEquals(book, bookService.createBook(book));
	}

	@Test
	public void testListAllBooks() {
		when(bookRepo.findAll()).thenReturn(bookList);
		assertEquals(bookList, bookService.listAllBooks());
	}


	@Test
	public void testFindBookById() {
		Book bookobj = new Book();
		bookobj.setBookId(101);
		when(bookRepo.findById(bookobj.getBookId())).thenReturn(Optional.of(bookobj));
		bookService.findBookById(bookobj.getBookId());
		verify(bookRepo).findById(bookobj.getBookId());
	}

	@Test
	public void testDeleteBook() {
		Book bookobj = new Book();
		bookobj.setBookId(101);
		when(bookRepo.findById(bookobj.getBookId())).thenReturn(Optional.of(bookobj));
		bookService.deleteBook(bookobj.getBookId());
		verify(bookRepo).deleteById(bookobj.getBookId());
	}

}