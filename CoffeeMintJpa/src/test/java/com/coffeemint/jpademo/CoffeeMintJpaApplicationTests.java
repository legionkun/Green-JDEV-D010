package com.coffeemint.jpademo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coffeemint.jpademo.models.Group;
import com.coffeemint.jpademo.models.User;
import com.coffeemint.jpademo.repository.GroupRepository;

@SpringBootTest
class CoffeeMintJpaApplicationTests {

	@Autowired
	GroupRepository groupRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createNewGroupWithuser() {
		Group group = new Group("FIN PRJ 4");
		
		User user = new User("AAA", "111");
		
		group.getUsers().add(user);
		
		groupRepo.save(group);
	}
	
	@Test
	void updateGroupAddNewuser() {
		Group group = groupRepo.getById(1);
		
		User user = new User("BBBB", "111");
		
		group.getUsers().add(user);
		
		groupRepo.save(group);
	}

}
