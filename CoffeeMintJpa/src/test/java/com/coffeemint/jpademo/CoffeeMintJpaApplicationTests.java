package com.coffeemint.jpademo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coffeemint.jpademo.models.Address;
import com.coffeemint.jpademo.models.Customer;
import com.coffeemint.jpademo.models.Group;
import com.coffeemint.jpademo.models.User;
import com.coffeemint.jpademo.repository.CustomerRepository;
import com.coffeemint.jpademo.repository.GroupRepository;

@SpringBootTest
class CoffeeMintJpaApplicationTests {

	@Autowired
	GroupRepository groupRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createCustomerWithAdress() {
		Customer customer = new Customer("Nguyen C", "Thang");
		
		Address address1 = new Address("12CC Nguyen Binh Khime", "HCM", null);
		
		customer.getAddresses().add(address1);
		
		customerRepo.save(customer);
	}
	
	@Test
	void deleteCustomer() {
		customerRepo.deleteById(3);
	}
	
	@Test
	void createNewGroupWithuser() {
		Group group = new Group("FIN PRJ 9");
		
		User user = new User("AAA2", "222");
		
		group.getUsers().add(user);
		
		groupRepo.save(group);
	}
	
	@Test
	void updateGroupAddNewuser() {
		Group group = groupRepo.getById(1);
		
		group.setName("FINAL PROJ GREEN 3");
		
		User user = new User("BBBB", "111");
		
		user.addGroup(group);
		
		group.getUsers().add(user);
		
		groupRepo.save(group);
	}

	@Test
	void updateGroupRemoveUser() {
		Group group = groupRepo.getById(1);
		
		group.setName("FINAL PROJ GREEN remove user");
		
		User user = group.getListUser().get(0);
		
		System.out.println("updateGroupRemoveUser: " + user.getId());
		
		group.getUsers().remove(user);
		
		groupRepo.save(group);
	}
}
