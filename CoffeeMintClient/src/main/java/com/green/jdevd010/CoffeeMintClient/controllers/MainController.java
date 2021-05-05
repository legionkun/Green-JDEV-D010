package com.green.jdevd010.CoffeeMintClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@GetMapping("/login")
	public String showLoginView() {
		
		return "login";
	}
	
	@GetMapping("/product")
	public String showProductView() {
		
		return "product";
	}
}
