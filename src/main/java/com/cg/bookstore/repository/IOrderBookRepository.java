package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Order;
import com.cg.bookstore.entities.OrderBook;
import com.cg.bookstore.entities.OrderBookPK;

@Repository
public interface IOrderBookRepository extends JpaRepository<OrderBook, OrderBookPK>{
	@Query(nativeQuery=true,value="select o.* from orderdetailsdb o"
			+ " where o.book_id = :bookId")
	List<OrderBook> findByBookId(int bookId);
	

}
