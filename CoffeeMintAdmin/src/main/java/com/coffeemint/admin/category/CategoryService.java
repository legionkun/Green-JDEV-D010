package com.coffeemint.admin.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeemint.models.entites.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> getRootCategory() {
		
		return repository.getRootCategory();
	}
	
	public Category getCategoryById(Integer id) {
		return repository.getById(id);
	}
	
	public List<Category> getCategoryParents(Category category) {
		
		
		return null;
	}
}
