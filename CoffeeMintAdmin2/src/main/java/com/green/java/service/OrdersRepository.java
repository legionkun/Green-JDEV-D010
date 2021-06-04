package com.green.java.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.java.entity.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer>{
	
}
