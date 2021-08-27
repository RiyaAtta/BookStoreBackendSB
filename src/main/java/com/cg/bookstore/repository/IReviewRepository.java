package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.dto.IBook;
import com.cg.bookstore.dto.IReview;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;

import com.cg.bookstore.entities.Review;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer>{


//	
//	@Query("select r.rating from Review r where r.book Like %?1" )
//	 List<Review> findByBookName(String bookName);

	public List<Review> findByBook_BookId(int bookId);
	public List<Review> findByCustomer_CustomerId(int customerId);

	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="select b.title, r.review_id, r.comments, r.headline, r.date_reviewon, r.ratings"
			+ " from reviewtabledb r "
			+ "inner join booktabledb b on r.book_book_id= b.book_id"
			+ " where r.cust_id = :custId")
	List<IReview> allReviewsByACustomer(@Param("custId") int custId);
	
    @Query(nativeQuery=true,value="select b.title, r.review_id, r.comments, r.headline, r.date_reviewon, r.ratings"
			+ " from reviewtabledb r "
			+ "inner join booktabledb b on r.book_book_id= b.book_id")
    public List<IReview> displayAll();
	
	@Query(nativeQuery=true,value="select b.title, c.fullname, r.review_id, r.comments, r.headline, r.date_reviewon, r.ratings"
			+ " from reviewtabledb r "
			+ "inner join customertabledb c on r.cust_id= c.cust_id"
			+ " inner join booktabledb b on r.book_book_id= b.book_id"
			+ " where r.book_book_id = :bookId")
	public List<IReview> allReviewsByBook(int bookId);

	@Query(nativeQuery=true,value="select b.title, b.author, b.price, r.rating from reviewtabledb r"
			+ " inner join booktabledb b "
			+ "on r.book_book_id= b.book_id order by r.ratings desc")
	public List<IBook> listMostFavouredBooks();
	
	
	//@Query(nativeQuery=true,value="select r.review_id, r.comments, r.headline, r.date_reviewon from reviewtable r where r.cust_id = :custId")
	//List<IReview> allReviewsByACustomer(@Param("custId") int custId);

	//@Query(nativeQuery=true,value="select b.title, r.review_id, r.comments, r.headline, r.date_reviewon from reviewtable r inner join booktable b on r.book_book_id= b.book_id inner join customertable c on r.cust_id= c.cust_id where c.fullname = :custName")
	//List<IReview> allReviewsByACustomer(@Param("custName") String custName);
}

