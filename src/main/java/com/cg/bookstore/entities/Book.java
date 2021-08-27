package com.cg.bookstore.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "booktabledb")
@ApiModel(description = "Book")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "bookSeqGenn", sequenceName = "bookSeqq", initialValue = 100)
	@GeneratedValue(generator = "bookSeqGenn")
	@Column(name = "book_id")
	private int bookId;

	@ApiModelProperty(notes = "Title of the Book")
	@Size(max = 40, min = 1, message = "book title invalid")
	@NotEmpty(message = "Please enter title")
	@Column(name = "title", nullable = false)
	private String title;

	@ApiModelProperty(notes = "Author of the book")
	@NotEmpty(message = "Cannot be blank")
	@Column(name = "author")
	private String author;

	@ApiModelProperty(notes = "Category of the book")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id")
	private Category category;

	@ApiModelProperty(notes = "Description of the book")
	@NotBlank
	@Lob
	@Column(name = "description")
	private String description;

	@ApiModelProperty(notes = "ISBN of the book")
	@Column(name = "isbn")
	private String isbn;

	@ApiModelProperty(notes = "Price of the book")
	@NotNull(message = "Price invalid")
	@Min(1)
	@Column(name = "price")
	private double price;

	@ApiModelProperty(notes = "Published date of the book")
	@Column(name = "publishdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	//@NotNull(message = "Please provide a date.")
	private LocalDate publishDate;
	
	
	@Column(name="picture",length=1000)
	private byte[] bytes;
	

public Book(
			@Size(max = 40, min = 1, message = "book title invalid") @NotEmpty(message = "Please enter title") String title,
			@NotEmpty(message = "Cannot be blank") String author, Category category, @NotBlank String description,
			String isbn, @NotNull(message = "Price invalid") @Min(1) double price, LocalDate publishDate,
			byte[] bytes) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		this.publishDate = publishDate;
		this.bytes = bytes;
	}


//
//	@ApiModelProperty(notes = "Last update of the book")
//	@Column(name = "lastupdatedon")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	//@NotNull(message = "Please provide a date.")
//	private LocalDate lastUpdatedOn;
	
	public Book(String title, String author, Category category , String description, String isbn, double price
			/*LocalDate publishDate, LocalDate lastUpdatedOn*/) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		//this.publishDate = publishDate;
		//this.lastUpdatedOn = lastUpdatedOn;
	}


	public Book() {
		super();
	}


	public Book(byte[] bytes) {
		// TODO Auto-generated constructor stub
		this.bytes=bytes;
	}


	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	  public Category getCategory() { return category; }
	  
	  public void setCategory(Category category) { this.category = category; }
	 
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}
//
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	public byte[] getBytes() {
		return bytes;
	}


	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

//
//	public LocalDate getLastUpdatedOn() {
//		return lastUpdatedOn;
//	}
//
//	public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
//		this.lastUpdatedOn = lastUpdatedOn;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		//result = prime * result + ((lastUpdatedOn == null) ? 0 : lastUpdatedOn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookId != other.bookId)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
//		if (lastUpdatedOn == null) {
//			if (other.lastUpdatedOn != null)
//				return false;
//		} else if (!lastUpdatedOn.equals(other.lastUpdatedOn))
//			return false;
//		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
//			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}