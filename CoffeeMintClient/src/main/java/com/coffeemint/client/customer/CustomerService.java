package com.coffeemint.client.customer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeemint.models.emnumered.AuthProvider;
import com.coffeemint.models.entites.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	public List<Customer> listAllCustomer() {
		return repository.findAll();
	}
	
	public Customer getByEmail(String email) {
		return repository.getByEmail(email);
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
		
		repository.save(customer);
	}
	
	public void updateCustomer(Customer customer, String lastName, String firstName, AuthProvider provider) {
		
		Date date = new Date();
		
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setLastLogin(date);
		
		repository.save(customer);
	}
	
	public void saveProfile(Customer customer) {
		repository.save(customer);
	}
}
