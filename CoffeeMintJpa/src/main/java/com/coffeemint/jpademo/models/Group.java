package com.coffeemint.jpademo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbgroups")
public class Group {

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 64)
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "groups_users",
	        joinColumns = @JoinColumn(name = "group_id"),
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<User> users = new HashSet<User>();
	
	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Group(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@Transient
	public ArrayList<User> getListUser() {
		ArrayList<User> listUser = new ArrayList<>();
		
		for(User user : users) {
			listUser.add(user);
		}
		
		return listUser;
	}
}
