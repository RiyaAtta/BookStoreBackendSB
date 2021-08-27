package com.cg.bookstore.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Order;
import com.cg.bookstore.entities.OrderBook;

@Service
public interface IOrderService {
	 public Iterable<Order> getAllOrders() ;
	 public void update(Order order);
	 public Order create(Order order) ;
	 public ResponseEntity<String> cancelOrder(Long ordId);
	@NotNull
	Order getOrder(Long orderId);
	public @NotNull List<OrderBook> getOrdersByBook(int bookId);
	public List<Order> getOrderByEmail(String email);

}
