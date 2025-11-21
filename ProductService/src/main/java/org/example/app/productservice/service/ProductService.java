package org.example.app.productservice.service;

import org.example.app.productservice.dto.ProductRequestDto;
import org.example.app.productservice.dto.ProductResponseDto;
import org.example.app.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public String deleteProduct(int id);
    public ProductResponseDto mapToProductResponseDto(Product product);
    public Product searchProduct(String productName);

    public Product mapToProduct(ProductRequestDto productRequestDto);
}
