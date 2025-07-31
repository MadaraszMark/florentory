package hu.florentory.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.florentory.main.dto.ProductRequest;
import hu.florentory.main.dto.ProductResponse;
import hu.florentory.main.exception.ResourceNotFoundException;
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
	    List<Products> all = productRepository.findAll();
	    if (all.isEmpty()) {
	        throw new ResourceNotFoundException("Nincsenek elérhető termékek.");
	    }
	    return all.stream().map(ProductMapper::toResponse).toList();
	}
	
	public ProductResponse addProduct(ProductRequest request) {
		Products entity = ProductMapper.toEntity(request);
		Products saved = productRepository.save(entity);
		return ProductMapper.toResponse(saved);
	}
	
	public List<ProductResponse> getProductsCheaperThan(double price){
	    List<Products> products = productRepository.findByPriceLessThan(price);
	    if (products.isEmpty()) {
	        throw new ResourceNotFoundException("Nem található termék " + price + " Ft alatti áron.");
	    }
	    return products.stream().map(ProductMapper::toResponse).toList();
	}
	
	public List<ProductResponse> getProductsByName(String name){
	    List<Products> products = productRepository.findByNameIgnoreCase(name);
	    if (products.isEmpty()) {
	        throw new ResourceNotFoundException("Nem található termék ezzel a névvel: " + name);
	    }
	    return products.stream().map(ProductMapper::toResponse).toList();
	}
	
	public Optional<ProductResponse> getProductBySku(String sku){
	    return productRepository.findBySkuIgnoreCase(sku).stream().findFirst().map(ProductMapper::toResponse).or(() -> {
	            throw new ResourceNotFoundException("SKU nem található: " + sku);
	        });
	}
	
	public List<ProductResponse> getProductByCategoryId(Long id){
	    List<Products> products = productRepository.findByCategoryId(id);
	    if (products.isEmpty()) {
	        throw new ResourceNotFoundException("Nem található termék ebben a kategóriában: " + id);
	    }
	    return products.stream().map(ProductMapper::toResponse).toList();
	}
	
	public List<ProductResponse> getProductNameContaining(String keyword){
	    List<Products> products = productRepository.findByNameContainingIgnoreCase(keyword);
	    if (products.isEmpty()) {
	        throw new ResourceNotFoundException("Nincs találat a megadott kulcsszóra: " + keyword);
	    }
	    return products.stream().map(ProductMapper::toResponse).toList();
	}
	
	public List<ProductResponse> getProductByCreatedAtDesc(){
	    List<Products> products = productRepository.findAllByOrderByCreatedAtDesc();
	    if (products.isEmpty()) {
	        throw new ResourceNotFoundException("Nem található termék időrendben.");
	    }
	    return products.stream().map(ProductMapper::toResponse).toList();
	}
	
	public Optional<ProductResponse> getCheapestProduct() {
	    return productRepository.findFirstByOrderByPriceAsc().map(ProductMapper::toResponse);
	}
	
}
