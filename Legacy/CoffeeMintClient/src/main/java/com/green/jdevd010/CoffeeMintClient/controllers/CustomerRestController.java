package com.green.jdevd010.CoffeeMintClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.jdevd010.CoffeeMintClient.controllers.services.CustomerService;
import com.green.jdevd010.CoffeeMintClient.models.Customer;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register/check_email")
	public String checkEmailExisted(@Param("email") String email) {
		
		Customer customer = customerService.getByEmail(email);
		
		if (customer == null) {
			//return JSON of customer;
			System.out.println("checkEmailExisted: not exist " + email);
			return "NotExist";
		}
		
		System.out.println("checkEmailExisted: existed " + email);
		
		return "Existed";
	}
	
	@GetMapping("/register/check_email")
	public String getEmailExisted(@Param("email") String email) {
		
		Customer customer = customerService.getByEmail(email);
		
		if (customer == null) {
			
			System.out.println("checkEmailExisted: not exist " + email);
			return "NotExist";
		}
		
		//return JSON of customer;
		System.out.println("checkEmailExisted: existed " + email);
		
		return "Existed";
	}
}
