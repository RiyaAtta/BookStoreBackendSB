package com.cg.bookstore.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.bookstore.dto.OrderBookDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Order;
import com.cg.bookstore.entities.OrderBook;
import com.cg.bookstore.entities.OrderStatus;
import com.cg.bookstore.service.CustomerServiceImpl;
import com.cg.bookstore.service.OrderBookServiceImpl;
import com.cg.bookstore.service.OrderServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderServiceImpl orderServiceImpl;
	@MockBean
	private OrderBookServiceImpl bookOrderServiceImpl;
	

	@Autowired
	private ObjectMapper objectMapper;
	
	private List<Order> orderList;
	private List<OrderBook> bookOrdersList;
	
	@Before
    void setUp()throws Exception {
		this.orderList=new ArrayList<>();
		this.bookOrdersList=new ArrayList<>();
		Order order=new Order();
		order=new Order();
		order.setId(1L);
		order.setDateCreated(LocalDate.now());
        order.setStatus(OrderStatus.PAID.name());
		//this.orderList.add(order);
		Category c=new Category("Fiction");
		c.setCategoryId(1);
		byte[] anyInputStream = null ;
		Book b= new Book( "JK", "HP", c, "Nice", "133545", 800, LocalDate.now(), anyInputStream);
		this.bookOrdersList.add(new OrderBook(order, b, 5));

        order.setOrderBooks(bookOrdersList);
        order.setTotalPrice(order.getTotalPrice());
        orderServiceImpl.update(order);
        this.orderList.add(order);

		//this.bookOrdersList.add(new OrderBook(order, book, quantity))
	}
//	@Test
//	void testCancelOrder() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testOrderDetailsInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testViewOrder() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testOrderDetails() {
//		fail("Not yet implemented");
//	}

	@Test
	void testCreate() throws JsonProcessingException, Exception {
		Order ordernew=new Order();
        ordernew.setStatus(OrderStatus.PAID.name());
        ordernew.setRecipientName("Mahesh");
        byte[] anyInputStream = null ;
        ordernew.setRecipientPhone("7449980993");
        ordernew.setShippingAddress("Kolkata");
        assertEquals("1", this.orderList);
		Mockito.when(orderServiceImpl.create(ordernew)).thenReturn(this.orderList.get(0));
		//given(orderServiceImpl.create(orderList.get(0))).willReturn(value, values));
//		this.mockMvc.perform(post("/orders/addorder").param("name", "Mahesh").param("phone", "7449980993").param("payment", "CC").param("address", "Kolkata")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(orderList.get(0))))
//				.andExpect(status().isOk());	
	}

}
