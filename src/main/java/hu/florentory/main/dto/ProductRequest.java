package hu.florentory.main.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {
	
	@NotBlank(message = "Az SKU megadása kötelező!")
    private String sku;
	@NotBlank(message = "Név az kötelező!")
	private String name;
	@NotNull(message = "A kategória ID megadása kötelező!")
	@Min(value = 1, message = "A kategória ID legalább 1 kell legyen!")
	private Long categoryId;
	private String description;
	@Min(value = 0, message = "Az árnak pozitívnak kell lennie!")
	private double price;
	
	public ProductRequest() {
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
