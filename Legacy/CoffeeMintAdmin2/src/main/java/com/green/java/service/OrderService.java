package com.green.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		
		Pageable pageable;
		
		if (pageNum >= 1) {
			pageable = PageRequest.of(pageNum - 1, OrderService.PAGE_SIZE);
		} else {
			pageable = PageRequest.of(0, OrderService.PAGE_SIZE);
		}
		return ordersRepository.findAll(pageable);
	}
	
	public Page<Order> getAllOrders(int pageNum, String sortBy, String sortDirection) {
		Sort sort = Sort.by(sortBy);
		
		if (sortDirection.equals("asc")) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		
		Pageable pageable;
		
		if (pageNum >= 1) {
			pageable = PageRequest.of(pageNum - 1, OrderService.PAGE_SIZE, sort);
		} else {
			pageable = PageRequest.of(0, OrderService.PAGE_SIZE, sort);
		}
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
