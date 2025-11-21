package org.example.app.productservice.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.app.productservice.dto.ProductRequestDto;
import org.example.app.productservice.dto.ProductResponseDto;
import org.example.app.productservice.model.Product;
import org.example.app.productservice.repository.ProductRepository;
import org.example.app.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findByAvailableTrue();
    }


    @Override
    public Product addProduct(Product product) {
       product.setAvailable(true);
       return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productToBeUpdated = productRepository.findById(product.getId());
        if (productToBeUpdated.isPresent()) {
            product.setAvailable(true);
            return productRepository.save(product);
        }
        else
            return null;


    }

    @Override
    public String deleteProduct(int id) {

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
        {
            Product productToBeDeleted = product.get();
            productToBeDeleted.setAvailable(false);
            productRepository.save(productToBeDeleted);
            return "Product Deleted";
        }
        else
            return null;

    }

    @Override
    public ProductResponseDto mapToProductResponseDto(Product product) {
         ProductResponseDto productResponseDto = new ProductResponseDto();
         productResponseDto.setAvailable(product.getAvailable());
         productResponseDto.setCategory(product.getCategory());
         productResponseDto.setDescription(product.getDescription());
         productResponseDto.setImageUrl(product.getImageUrl());
         productResponseDto.setName(product.getName());
         productResponseDto.setPrice(product.getPrice());
         return productResponseDto;
    }

    @Override
    public Product searchProduct(String productName) {
         return productRepository.searchByName(productName);
    }

    @Override
    public Product mapToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(productRequestDto.getCategory());
        return product;
    }
}
