package hu.florentory.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.florentory.main.dto.ProductRequest;
import hu.florentory.main.dto.ProductResponse;
import hu.florentory.main.mapper.ProductMapper;
import hu.florentory.main.model.Products;
import hu.florentory.main.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<ProductResponse> getAllProduct() {
		return productRepository.findAll().stream().map(ProductMapper::toResponse).toList();
	}
	
	public ProductResponse addProduct(ProductRequest request) {
		Products entity = ProductMapper.toEntity(request);
		Products saved = productRepository.save(entity);
		return ProductMapper.toResponse(saved);
	}
	
	public List<ProductResponse> getProductsCheaperThan(double price){
		return productRepository.findByPriceLessThan(price).stream().map(ProductMapper::toResponse).toList();
	}
	
	public List<ProductResponse> getProductsByName(String name){
		return productRepository.findByNameIgnoreCase(name).stream().map(ProductMapper::toResponse).toList();
	}
	
	public Optional<ProductResponse> getProductBySku(String sku) {
		return productRepository.findBySkuIgnoreCase(sku).stream().findFirst().map(ProductMapper::toResponse);
	}
	
	public List<ProductResponse> getProductByCategoryId(Long id){
		return productRepository.findByCategoryId(id).stream().map(ProductMapper::toResponse).toList();
	}
	
	public List<ProductResponse> getProductNameContaining(String keyword){
		return productRepository.findByNameContainingIgnoreCase(keyword).stream().map(ProductMapper::toResponse).toList();
	}
	
	public List<ProductResponse> getProductByCreatedAtDesc(){
		return productRepository.findAllByOrderByCreatedAtDesc().stream().map(ProductMapper::toResponse).toList();
	}
	
	public Optional<ProductResponse> getCheapestProduct() {
	    return productRepository.findFirstByOrderByPriceAsc().map(ProductMapper::toResponse);
	}

	
}
