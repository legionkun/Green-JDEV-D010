package com.green.jdevd010.CoffeeMintClient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("Pass was Gen: " + encoder.encode("123456"));

	}

}
