package com.coffeemint.jpademo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1404010998472982660L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;

	@Column(name = "STREET", unique = false, nullable = false, length = 128)
	private String street;

	@Column(name = "CITY", unique = false, nullable = false, length = 128)
	private String city;

	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customer;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String street, String city, Customer customer) {
		super();
		this.street = street;
		this.city = city;
		this.customer = customer;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
