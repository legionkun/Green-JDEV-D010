package com.coffeemint.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan("com.coffeemint.models")
public class CoffeeMintAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeMintAdminApplication.class, args);
	}

}
