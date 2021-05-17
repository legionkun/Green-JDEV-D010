package com.green.jdevd010.CoffeeMintClient.controllers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jdevd010.CoffeeMintClient.daos.CustomerRepositopry;
import com.green.jdevd010.CoffeeMintClient.models.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepositopry repositopry;
	
	public List<Customer> listAllCustomer() {
		return repositopry.findAll();
	}
	
	public Customer getByEmail(String email) {
		return repositopry.getByEmail(email);
	}
}
