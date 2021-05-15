package com.green.jdevd010.CoffeeMintClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("/product")
	public String showProductView() {
		
		return "product";
	}
	
	@GetMapping("/update_product")
	public String showUpdateProductView() {
		return "update_product";
	}
}
