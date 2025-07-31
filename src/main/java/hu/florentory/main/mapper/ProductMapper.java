package hu.florentory.main.mapper;

import java.time.LocalDateTime;

import hu.florentory.main.dto.ProductRequest;
import hu.florentory.main.dto.ProductResponse;
import hu.florentory.main.model.Products;

public class ProductMapper {
	
	public static Products toEntity(ProductRequest request) {
		Products productObj = new Products();
		productObj.setSku(request.getSku());
		productObj.setName(request.getName());
		productObj.setCategoryId(request.getCategoryId());
		productObj.setDescription(request.getDescription());
		productObj.setPrice(request.getPrice());
		productObj.setCreatedAt(LocalDateTime.now());
		return productObj;
	}
	
	public static ProductResponse toResponse(Products products) {
		ProductResponse productResponseObj = new ProductResponse();
		productResponseObj.setId(products.getId());
		productResponseObj.setCategoryId(products.getCategoryId());
		productResponseObj.setSku(products.getSku());
		productResponseObj.setName(products.getName());
		productResponseObj.setDescription(products.getDescription());
		productResponseObj.setPrice(products.getPrice());
		productResponseObj.setCreatedAt(products.getCreatedAt());
		productResponseObj.setUpdatedAt(products.getUpdatedAt());
		return productResponseObj;
	}

}
