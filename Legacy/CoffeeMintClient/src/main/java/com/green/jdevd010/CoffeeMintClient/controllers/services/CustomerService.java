package com.green.jdevd010.CoffeeMintClient.controllers.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jdevd010.CoffeeMintClient.appenum.AuthProvider;
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
	
	public void registerNewCustomer(String email, String lastName, String firstName, AuthProvider provider) {
		Date createDate = new Date();
		Customer customer = new Customer();
		
		customer.setEnabled(true);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setCreateDate(createDate);
		customer.setLastLogin(createDate);
		customer.setAuthProvider(provider);
		
		repositopry.save(customer);
	}
	
	public void updateCustomer(Customer customer, String lastName, String firstName, AuthProvider provider) {
		
		Date date = new Date();
		
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setLastLogin(date);
		
		repositopry.save(customer);
	}
	
	public void saveProfile(Customer customer) {
		repositopry.save(customer);
	}
}
