package hu.florentory.main.fx.bridge;

import java.util.List;

import org.springframework.stereotype.Component;

import hu.florentory.main.fx.model.ProductTableModel;
import hu.florentory.main.service.ProductService;

@Component
public class ProductFxBridge {

    private final ProductService productService;

    public ProductFxBridge(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductTableModel> getAllProducts() {
        return productService.getAllProduct().stream()
            .map(dto -> new ProductTableModel(
                dto.getId(),
                dto.getName(),
                dto.getQuantity(),
                dto.getPrice(),
                dto.getCategoryName() // győződj meg róla, hogy van ilyen getter
            ))
            .toList();
    }
}
