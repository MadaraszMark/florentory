package hu.florentory.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_categories")
public class ProductCategories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	private String description;
	
	public ProductCategories(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public ProductCategories(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public ProductCategories() {
		// Hibernate
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
