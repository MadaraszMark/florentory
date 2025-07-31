package hu.florentory.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.florentory.main.dto.ProductRequest;
import hu.florentory.main.dto.ProductResponse;
import hu.florentory.main.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<ProductResponse> getAllProduct(){
		return productService.getAllProduct();
	}
	
	@PostMapping
	public ProductResponse addProduct(@RequestBody @Valid ProductRequest request) {
		return productService.addProduct(request);
	}
	
	@GetMapping("/cheaper-than/{price}")
	public List<ProductResponse> getProductsCheaperThan(@PathVariable double price){
		return productService.getProductsCheaperThan(price);
	}
	
	@GetMapping("/by-name/{name}")
	public List<ProductResponse> getProductsByName(@PathVariable String name){
		return productService.getProductsByName(name);
	}
	
	@GetMapping("/by-sku/{sku}")
	public ProductResponse getProductBySku(@PathVariable String sku) {
	    return productService.getProductBySku(sku).orElseThrow(() -> new RuntimeException("SKU nem található: " + sku));
	}
	
	@GetMapping("/by-category-id/{id}")
	public List<ProductResponse> getProductByCategoryId(@PathVariable Long id){
		return productService.getProductByCategoryId(id);
	}
	
	@GetMapping("/by-keyword/{keyword}")
	public List<ProductResponse> getProductByContainingName(@PathVariable String keyword){
		return productService.getProductNameContaining(keyword);
	}
	
	@GetMapping("/latest")
	public List<ProductResponse> getProductByCreatedAtDesc() {
		return productService.getProductByCreatedAtDesc();
	}

	@GetMapping("/cheapest")
	public ProductResponse getByOrderByPriceAsc() {
		return productService.getCheapestProduct().stream().findFirst().orElseThrow(() -> new RuntimeException("Nem találtunk terméket."));
	}

}
