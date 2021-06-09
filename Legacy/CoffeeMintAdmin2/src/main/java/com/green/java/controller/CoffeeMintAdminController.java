package com.green.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
		return showIndex(model, 1, null, null);
	}
	
	@GetMapping("/page/{pageNum}")
	public String showIndex(Model model, @PathVariable(name = "pageNum") int pageNum, @Param("sortBy") String sortBy, 
			 @Param("sortDirection") String sortDirection) {
		
		System.out.println("sortBy: " + sortBy  + " ==  " + "sortDirection");
		
		String direction = "asc";
		
		if (sortDirection != null && sortDirection.equals("asc")) {
			direction = "desc";
		}
		
		if (sortBy == null) {
			sortBy = "name";
		}
		
		Page<Order> page = orderService.getAllOrders(pageNum, sortBy, direction);
		List<Order> orderList = page.getContent();
		
		long startCount = (pageNum -1) * OrderService.PAGE_SIZE + 1;
		long endCount = startCount + OrderService.PAGE_SIZE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNum); 
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		
		//Sort
		model.addAttribute("direction", direction);
		model.addAttribute("sortBy", sortBy);
		
		return "data";
	}
}
