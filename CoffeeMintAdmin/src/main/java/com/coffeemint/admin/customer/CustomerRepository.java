package com.coffeemint.admin.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffeemint.models.entites.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
