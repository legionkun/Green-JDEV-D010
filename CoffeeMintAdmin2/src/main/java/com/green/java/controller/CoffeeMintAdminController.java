package com.green.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.java.entity.Order;
import com.green.java.service.OrderService;

@Controller
public class CoffeeMintAdminController {
	@Autowired
	private OrderService orderService;
	
//	@GetMapping("/")
//	public String showIndex(Model model) {
//		List<Order> orderList = orderService.getAllOrders();
//		model.addAttribute("orderList", orderList);
//		return "data";
//	}
	
	@GetMapping("/")
	public String showIndex(Model model) {
		return showIndex(model, 1);
	}
	
	@GetMapping("/page/{pageNum}")
	public String showIndex(Model model, @PathVariable(name = "pageNum") int pageNum) {
		
		Page<Order> page = orderService.getAllOrders(pageNum);
		
		List<Order> orderList = page.getContent();
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNum);
		
		return "data";
	}
}
