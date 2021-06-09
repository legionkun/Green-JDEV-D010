package com.green.jdevd010.CoffeeMintClient.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.jdevd010.CoffeeMintClient.controllers.services.CustomerService;
import com.green.jdevd010.CoffeeMintClient.helpers.FileUploadHelper;
import com.green.jdevd010.CoffeeMintClient.models.Customer;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/profile")
	public String showProfileView(Authentication auth, Model model) {
	
		Customer customer = customerService.getByEmail(auth.getName());
		
		model.addAttribute("customer", customer);
		
		return "profile";
	}
	
	@PostMapping("/save_profile")
	public String saveProfile(@RequestParam("fileImage") MultipartFile multipartFile,
			@ModelAttribute("customer") Customer formCustomer) {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println("saveProfile photo path: " + fileName + " for user id" + formCustomer.getId());
		
		//Base64 encode for filename
		String encodeFileName = Base64.getEncoder().encodeToString(fileName.split(".")[0].getBytes()) + "." + fileName.split(".")[1];
		
		formCustomer.setPhoto(encodeFileName);
		customerService.saveProfile(formCustomer);
		
		String uploadDir = "profile-photos/" + formCustomer.getId() ;
		
		System.out.println("uploadDir: " + uploadDir);
		try {
			FileUploadHelper.saveFile(uploadDir, encodeFileName, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
