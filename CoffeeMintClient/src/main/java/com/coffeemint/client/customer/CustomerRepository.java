package com.coffeemint.client.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coffeemint.models.entites.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("SELECT customer FROM Customer customer WHERE customer.email = :email")
	public Customer getByEmail(@Param("email") String email);
}
