package com.cg.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.bookstore.dto.OrderBookDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Order;
import com.cg.bookstore.entities.OrderBook;
import com.cg.bookstore.entities.OrderStatus;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.service.BookServiceImpl;
import com.cg.bookstore.service.IOrderService;
import com.cg.bookstore.service.OrderBookServiceImpl;
import com.cg.bookstore.service.OrderServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(value = "Order", description = "Operations pertaining to order of books")
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200") 
public class OrderController {

	@Autowired
	BookServiceImpl bookService;
	@Autowired
	OrderServiceImpl orderService;
	@Autowired
	OrderBookServiceImpl orderBookService;

	@GetMapping("/cancelorder/{ordId}")
	public ResponseEntity<String> cancelOrder(@PathVariable("ordId") Long ordId) {

		return orderService.cancelOrder(ordId);

	}
	//String status,Long ordId
	@GetMapping("/updateOrder/{status}/{ordId}")
	public String updateOrder(@PathVariable("status") String status,@PathVariable("ordId") Long ordId) {

		return orderService.updateStatus(status, ordId);

	}

	@ApiOperation(value = "view orders of the book", notes = "To view orders of the book", response = Contact.class)
	@GetMapping("/vieworderbybook/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public @NotNull List<OrderBook> orderDetails(@PathVariable("bookId") int bookId) {
		return this.orderService.getOrdersByBook(bookId);
	}
	
	@ApiOperation(value = "view the order by the customer", notes = "To view the details of a order", response = Contact.class)
	@GetMapping("/vieworderforcustomerbyemail/{email}")
	@ResponseStatus(HttpStatus.OK)
	public List<Order> viewOrderByEmail(@PathVariable("email") String email) {
		return this.orderService.getOrderByEmail(email);
	}

	@ApiOperation(value = "view the order by the customer", notes = "To view the details of a order", response = Contact.class)
	@GetMapping("/vieworderforcustomer/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Order viewOrder(@PathVariable("orderId") Long orderId) {
		return this.orderService.getOrder(orderId);
	}

	@ApiOperation(value = "view all the orders", notes = "To view all the orders by the admin", response = Contact.class)
	@GetMapping("/vieworderforadmin")
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Order> orderDetails() {
		return this.orderService.getAllOrders();
	}

	@ApiOperation(value = "books to be ordered", notes = "To order books by the customer", response = Contact.class)
	@PostMapping("/addorder")
	public ResponseEntity<Order> create(@RequestBody List<OrderBookDto> formDtos/*OrderForm form*/,
			@RequestParam(name = "name", required = true) String recipientName,
			@RequestParam(name = "phone", required = true) String phoneNumber,
			@RequestParam(name="email",required=true)String email,
			@RequestParam(name = "address", required = true) String shippingAddress) {
		//List<OrderBookDto> formDtos = form.getBookOrders();
		//@RequestParam(name = "payment", required = true) String paymentMethod,
		validateBooksExistence(formDtos);
		Order order = new Order();
		order.setStatus(OrderStatus.PAID.name());
		//order.setPaymentMethod(paymentMethod);
		order.setRecipientName(recipientName);
		order.setRecipientPhone(phoneNumber);
		order.setCustomerEmail(email);
		order.setShippingAddress(shippingAddress);
		order = this.orderService.create(order);

		List<OrderBook> orderBooks = new ArrayList<>();
		for (OrderBookDto dto : formDtos) {
			orderBooks.add(orderBookService
					.create(new OrderBook(order, bookService.getBook(dto.getBook().getBookId()), dto.getQuantity())));
		}

		order.setOrderBooks(orderBooks);
		order.setTotalPrice(order.getTotalPrice());

		this.orderService.update(order);

		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}

	private void validateBooksExistence(List<OrderBookDto> orderBooks) {
		List<OrderBookDto> list = orderBooks.stream()
				.filter(op -> Objects.isNull(bookService.getBook(op.getBook().getBookId())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			new CustomException("Book not found");
		}
	}

	public static class OrderForm {

		private List<OrderBookDto> bookOrders;

		public List<OrderBookDto> getBookOrders() {
			return bookOrders;
		}

		public void setBookOrders(List<OrderBookDto> bookOrders) {
			this.bookOrders = bookOrders;
		}
	}
}