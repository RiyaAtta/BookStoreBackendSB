package com.cg.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.entities.OrderBook;
import com.cg.bookstore.repository.IOrderBookRepository;

@Service
@Transactional
public class OrderBookServiceImpl implements IOrderBookService{
	@Autowired
    private IOrderBookRepository orderBookRepository;
	
	@Override
    public OrderBook create(OrderBook orderBook) {
        return this.orderBookRepository.save(orderBook);
    }
}