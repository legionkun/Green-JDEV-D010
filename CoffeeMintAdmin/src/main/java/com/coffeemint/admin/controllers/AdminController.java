package com.coffeemint.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coffeemint.admin.category.CategoryService;
import com.coffeemint.models.entites.Category;

@Controller
public class AdminController {

	@Autowired
	private CategoryService service;
	
	@RequestMapping("/")
	public String showDashboard() {
		
		return "index";
	}
	
	@RequestMapping("/about")
	public String showAbout() {
		
		return "about";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomeView(Model model) {
		List<Category> listCategories = service.getRootCategory();
		
		model.addAttribute("listCategories", listCategories);
		
		return "home";
	}
	
	@RequestMapping(value = "/home-category/{id}", method = RequestMethod.GET)
	public String showHomeCategoryView(@PathVariable("id") Long id, Model model) {
		
		Category category = service.getCategoryById(id);
		
		List<Category> listCategories = category.getListSubCategory();
		
		
		List<Category> listParents = service.getCategoryParents(category);
		
		model.addAttribute("listParents", listParents);
		model.addAttribute("listCategories", listCategories);
		
		
		
		return "home_category";
	}
}
