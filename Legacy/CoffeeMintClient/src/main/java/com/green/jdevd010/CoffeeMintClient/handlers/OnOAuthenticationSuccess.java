package com.green.jdevd010.CoffeeMintClient.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.green.jdevd010.CoffeeMintClient.appenum.AuthProvider;
import com.green.jdevd010.CoffeeMintClient.controllers.services.CustomerService;
import com.green.jdevd010.CoffeeMintClient.models.Customer;
import com.green.jdevd010.CoffeeMintClient.security.CustomOAuth2User;

@Component
public class OnOAuthenticationSuccess extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
		String email = oauth2User.getEmail();
		String name = oauth2User.getName();
		String servletPath  = request.getServletPath();
		
		System.out.println("email: " + email + " name: " + name + " servlet path: " + servletPath);
		
		AuthProvider provider = AuthProvider.BASIC;
		
		if (servletPath.contains("facebook")) {
			provider = AuthProvider.FACEBOOK;
		} else if (servletPath.contains("google")) {
			provider = AuthProvider.GOOGLE;
		}
		
		Customer customer = customerService.getByEmail(email);
		
		if (customer == null) {
			//Dang ky customer moi
			customerService.registerNewCustomer(email, "", name, provider);
		} else {
			//Cap nhat lai provider & info
			customerService.updateCustomer(customer, "", name, provider);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
