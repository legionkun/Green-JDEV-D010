package com.green.jdevd010.CoffeeMintClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.jdevd010.CoffeeMintClient.controllers.services.CustomerService;
import com.green.jdevd010.CoffeeMintClient.controllers.services.UserService;
import com.green.jdevd010.CoffeeMintClient.models.Customer;
import com.green.jdevd010.CoffeeMintClient.models.User;

@Controller
public class MainController {

	@Autowired
	private UserService userService;
	
	private CustomerService customerService;
	
	@GetMapping("/login")
	public String showLoginView() {
		
		return "login";
	}
	
	@GetMapping("/login2")
	public String showLogin2View(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);
		
		return "login2";
	}
	
	@PostMapping("/login2")
	public String doLogin2(@ModelAttribute("user") User formUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User dbUser = userService.getUserByUsername(formUser.getUsername());
		if (dbUser != null && encoder.matches(formUser.getPassword(), dbUser.getPassword())) {
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/login_error")
	public String showLoginError() {
		return "login_error";
	}
	
	@PostMapping("/register")
	public String doRegister(@ModelAttribute("customer") Customer formCustomer) {
		System.out.println("doRegister: " + formCustomer.getEmail());
		
		//save customer
		//1.check email chua ton taij -> encrypt password -> save 
		//2.email da ton tai -> view thong bao email da duoc su dung. yeu cau user ddang ky voi email khac.
		
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String showRegisterView(Model model) {
		Customer customer = new Customer();
		
		model.addAttribute("customer", customer);
		
		return "register";
	}
}
