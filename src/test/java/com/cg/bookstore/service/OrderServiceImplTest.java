package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.bookstore.entities.Order;
import com.cg.bookstore.entities.OrderStatus;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IOrderBookRepository;
import com.cg.bookstore.repository.IOrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

	@Autowired
	private OrderServiceImpl orderService;

	@MockBean
	private IOrderRepository orderRepo;
	@MockBean
	private IBookRepository bookRepo;
	@MockBean
	private IOrderBookRepository bookOrderRepo;

	@Test
	public void create() {
		Order order = new Order();
		order.setStatus(OrderStatus.PAID.name());
		//order.setPaymentMethod("CC");
		order.setRecipientName("shounak");
		order.setRecipientPhone("7631116004");
		order.setShippingAddress("dumdum");
		when(orderRepo.save(order)).thenReturn(order);
		assertEquals(order, orderService.create(order));
	}

	@Test
	public void cancelOrder() {
		Order order = new Order();
		order.setId(1L);
		when(orderRepo.findById(order.getId())).thenReturn(Optional.of(order));
		orderService.cancelOrder(order.getId());
		verify(orderRepo).deleteById(order.getId());
	}

	@Test
	public void getOrder() {
		Order order = new Order();
		order.setId(1L);
		when(orderRepo.findById(order.getId())).thenReturn(Optional.of(order));
		orderService.getOrder(order.getId());
	}

}
