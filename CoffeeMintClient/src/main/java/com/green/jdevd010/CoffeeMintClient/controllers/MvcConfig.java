package com.green.jdevd010.CoffeeMintClient.controllers;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("profile-photos", registry);
		exposeDirectory("product-photos", registry);
		exposeDirectory("category-photos", registry);
	}
	
	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		
		System.out.println("exposeDirectory: "  + uploadPath);
		
		if (dirName.startsWith("../")) {
			dirName.replace("../", "");
		}
		
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:" + uploadPath + "/");
	}
}
