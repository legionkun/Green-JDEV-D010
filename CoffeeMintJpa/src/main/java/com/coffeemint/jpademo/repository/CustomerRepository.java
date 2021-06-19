package com.coffeemint.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffeemint.jpademo.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
