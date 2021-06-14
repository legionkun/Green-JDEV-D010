package com.coffeemint.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/")
	public String showDashboard() {
		
		return "index";
	}
	
	@RequestMapping("/about")
	public String showAbout() {
		
		return "about";
	}
}
