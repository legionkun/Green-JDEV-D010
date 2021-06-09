package com.green.jdevd010.CoffeeMintClient.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.green.jdevd010.CoffeeMintClient.models.Customer;

public interface CustomerRepositopry extends JpaRepository<Customer, Integer> {

	@Query("SELECT customer FROM Customer customer WHERE customer.email = :email")
	public Customer getByEmail(@Param("email") String email);
}
