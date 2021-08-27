package com.cg.bookstore.service;

import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.OrderBook;

@Service
public interface IOrderBookService {
	 public OrderBook create(OrderBook orderBook);
}
