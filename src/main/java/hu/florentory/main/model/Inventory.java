package hu.florentory.main.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "location_id")
	private Long locationId;
	private int quantity;
	@Column(name = "last_stocked_at")
	private LocalDateTime lastStockedAt;
	
	public Inventory(Long id, Long productId, Long locationId, int quantity, LocalDateTime lastStockedAt) {
		this.id = id;
		this.productId = productId;
		this.locationId = locationId;
		this.quantity = quantity;
		this.lastStockedAt = lastStockedAt;
	}
	
	public Inventory(Long productId, Long locationId, int quantity, LocalDateTime lastStockedAt) {
		this.productId = productId;
		this.locationId = locationId;
		this.quantity = quantity;
		this.lastStockedAt = lastStockedAt;
	}
	
	public Inventory() {
		// Hibernate
	}

	public Long getId() {
		return id;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public int getQuantity() {
		return quantity;
	}

	public LocalDateTime getLastStockedAt() {
		return lastStockedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setLastStockedAt(LocalDateTime lastStockedAt) {
		this.lastStockedAt = lastStockedAt;
	}
}
