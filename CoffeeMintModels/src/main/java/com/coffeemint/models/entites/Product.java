package com.coffeemint.models.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "code", nullable = false, length = 64, unique = true)
	private String code;
	
	@Column(name = "name", nullable = false, length = 64, unique = true)
	private String name;
	
	@Column(name = "description", nullable = false, length = 256, unique = true)
	private String description;
	
	@Column(name = "photo", nullable = true, length = 128)
	private String photo;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column(name = "quantity", columnDefinition = "integer default '0'")
	private Integer quantity;
	
	@Column(name = "price", columnDefinition = "float default '0'")
	private float price;

	@Column(name = "sale_price", columnDefinition = "float default '0'")
	private float sale_price;
	
	@Column(name = "enabled")
	private Boolean enabled;
}