package com.green.jdevd010.CoffeeMintClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/login")
	public String showLoginView() {
		
		return "login";
	}
}
