package com.cg.bookstore.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import com.cg.bookstore.dto.Bookt;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.ImageModel;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.service.BookServiceImpl;
import com.cg.bookstore.service.IBookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Api(value="book",description="Operations pertaining to book")
@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200") 
public class BookController {
	private byte[] bytes;
	@Autowired
	private IBookService bookService;
	@Autowired
	private IBookRepository bookRepo;
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = compressBytes(file.getBytes());
	}

    @GetMapping(path = { "/get/{id}" })

    public Book getImage(@PathVariable("id") int id) throws IOException {

        final Optional<Book> retrievedImage = bookRepo.findById(id);

        Book img = new Book(

                decompressBytes(retrievedImage.get().getBytes()));
        System.out.println(retrievedImage.get().getBytes());

        return img;

    }
    

	
	@ApiOperation(value="add book details",notes="To add a book by the admin",response=Contact.class)
	@PostMapping("/addbook")
    ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
		//original=> return bookService.createBook(book);
        // persisting the book
		book.setBytes(this.bytes);
		//bookRepository.save(book);
    	ResponseEntity<Book> b= bookService.createBook(book);
		this.bytes = null;
		return b;
		
	}
	@ApiOperation(value="views all the book details",notes="To view all the books present",response=Contact.class)
	@GetMapping("/getallbooks")
	public List<Book> getBooks(){
		List<Book> temp= bookService.listAllBooks();
		for(Book i:temp) {
			i.setBytes(decompressBytes(i.getBytes()));
		}
		return temp;
	}
	@ApiOperation(value="search book by id",notes="To search a book by the book id given",response=Contact.class)
	@GetMapping("/searchbookbyid/{id}")
	public Book searchBookByID(@PathVariable("id") Integer bookId) {

		return bookService.findBookById(bookId);
	}
	@ApiOperation(value="views books by category",notes="To display the books according to the category",response=Contact.class)
	@GetMapping("/showbookbycategory/{category}")
	public List<Book> getBookByCategory(@PathVariable("category") String category){
		//return bookService.listBooksByCategory(category);
		List<Book> temp= bookService.listBooksByCategory(category);
		for(Book i:temp) {
			i.setBytes(decompressBytes(i.getBytes()));
		}
		return temp;
	}
	@ApiOperation(value="view books by book name entered",notes="displays the books according to the book name entered",response=Contact.class)
	@GetMapping("/viewbookbyname/{name}")
	public List<Book> getBooksByName(@PathVariable("name") String bookName){
		return bookService.viewBookByName(bookName);
	}

	@ApiOperation(value="update an existing book",notes="To change a book's details by the admin",response=Contact.class)
	@PutMapping("/updatebook")
	public Book updateBook(@RequestBody Bookt book){
		return bookService.editBook(book);
	}


	@ApiOperation(value="delete a book by the book id",notes="To delete a book by the book id entered by the admin",response=Contact.class)
	@DeleteMapping("/deletebook/{id}")
	public ResponseEntity<String> deleteBookingByID(@PathVariable("id") Integer bookId) {
	    try {
		      bookService.deleteBook(bookId);
		      return new ResponseEntity<String>(HttpStatus.OK);
		    }catch(RuntimeException ex){
		      // log the error message
		      System.out.println(ex.getMessage());
		      return new ResponseEntity<String>("Book not found!",HttpStatus.NOT_FOUND);
		    }
	}
    public static byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();

        deflater.setInput(data);

        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {

            int count = deflater.deflate(buffer);

            outputStream.write(buffer, 0, count);

        }

        try {

            outputStream.close();

        } catch (IOException e) {

        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();

    }
    
    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (IOException ioe) {

        } catch (DataFormatException e) {

        }

        return outputStream.toByteArray();

    }

}
