package com.green.jdevd010.CoffeeMintClient.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.green.jdevd010.CoffeeMintClient.daos.UserRepository;
import com.green.jdevd010.CoffeeMintClient.helpers.AppString;
import com.green.jdevd010.CoffeeMintClient.models.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(AppString.usernameNotFound);
		}
		
		
		return null;
	}


}
