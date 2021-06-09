package com.green.jdevd010.CoffeeMintClient.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

	private OAuth2User oauth2User;
	
	
	public CustomOAuth2User(OAuth2User oauth2User) {
		super();
		this.oauth2User = oauth2User;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oauth2User.getAuthorities();
	}

	@Override
	public String getName() {
		System.out.println("CustomOAuth2User:getName = " + oauth2User.getName() );
		return oauth2User.getName();
	}
	
	public String getEmail() {
		System.out.println("CustomOAuth2User::getEmail = " + oauth2User.getAttribute("email"));
		return oauth2User.getAttribute("email");
	}

}
