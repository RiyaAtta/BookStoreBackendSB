package com.cg.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.entities.Order;
import com.cg.bookstore.entities.OrderBook;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IOrderBookRepository;
import com.cg.bookstore.repository.IOrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{
	@Autowired
    private IOrderRepository orderRepository;
	@Autowired
	private IBookRepository bookRepo;
	@Autowired
	private IOrderBookRepository bookOrderRepo;

	@Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
	
	@Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());

        return this.orderRepository.save(order);
    }

	@Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
	/*
	 * update status
	 */
    public String updateStatus(String status,Long ordId) {
        Order temp=orderRepository.findById(ordId).get();
        temp.setStatus(status);
        return "Updated";
    }

	@Override
	public ResponseEntity<String> cancelOrder(Long ordId) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(ordId);
		return new ResponseEntity<String>("Order Deleted!",HttpStatus.OK);
	}
	@Override

	public @NotNull Order getOrder(Long orderId) {
		// TODO Auto-generated method stub
	 Optional<Order> orderPresentOrNot=orderRepository.findById(orderId);
	 if(orderPresentOrNot.isPresent()) {
		 return orderPresentOrNot.get();
	 }
	 else {
		 throw new CustomException("Order not found");
	 }
	}
	@Override
	public @NotNull List<OrderBook> getOrdersByBook(int bookId) {
		// TODO Auto-generated method stub
   	 return bookRepo.findById(bookId).map(book->{
		 return bookOrderRepo.findByBookId(bookId);
	 }).orElseThrow(()->new CustomException("Book Not Present"));
}
	@Override
	public List<Order> getOrderByEmail(String email) {
		// TODO Auto-generated method stub
		return orderRepository.findByCustomerEmail(email);
	}
}