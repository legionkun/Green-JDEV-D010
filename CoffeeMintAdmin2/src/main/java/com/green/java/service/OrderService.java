package com.green.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.java.entity.Order;


@Service
@Transactional
public class OrderService {
	
	public static int PAGE_SIZE = 5;  
	
	@Autowired
	private OrdersRepository ordersRepository;

	public Page<Order> getAllOrders(int pageNum) {
		
		Pageable pageable = PageRequest.of(pageNum - 1, OrderService.PAGE_SIZE);
		
		return ordersRepository.findAll(pageable);
	}

	public void addOrder(Order order) {
		ordersRepository.save(order);
	}
	
	public void deleteOrder(Integer id) {
		ordersRepository.deleteById(id);	
	}

	public Order getById(Integer id) {
		return ordersRepository.findById(id).get();
	}
	
	
}
