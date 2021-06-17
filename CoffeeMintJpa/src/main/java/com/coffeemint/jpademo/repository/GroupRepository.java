package com.coffeemint.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffeemint.jpademo.models.Group;

public interface GroupRepository extends JpaRepository<Group, Integer>{

}
