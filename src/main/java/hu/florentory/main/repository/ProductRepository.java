package hu.florentory.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.florentory.main.model.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{
	List<Products> findByPriceLessThan(double price);
	List<Products> findByNameIgnoreCase(String name);
	List<Products> findBySkuIgnoreCase(String sku);
	List<Products> findByCategoryId(Long id);
	List<Products> findByNameContainingIgnoreCase(String keyword);
	List<Products> findAllByOrderByCreatedAtDesc();
	Optional<Products> findFirstByOrderByPriceAsc();
}
