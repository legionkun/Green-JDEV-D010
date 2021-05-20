package com.green.jdevd010.CoffeeMintClient.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.green.jdevd010.CoffeeMintClient.daos.CustomerRepositopry;
import com.green.jdevd010.CoffeeMintClient.helpers.AppString;
import com.green.jdevd010.CoffeeMintClient.models.Customer;
import com.green.jdevd010.CoffeeMintClient.security.MyCustomerDetails;
import com.green.jdevd010.CoffeeMintClient.security.MyUserDetails;

public class CustomerDetailServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepositopry customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepo.getByEmail(username);
		
		if (customer == null) {
			System.out.println("load by email not found");
			throw new UsernameNotFoundException(AppString.customerEmailNotFound);
		}
		
		System.out.println("loadUserByUsername ** : " + customer.getEmail());
		
		return new MyCustomerDetails(customer);
	}

}
