package org.example.app.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.app.productservice.dto.ProductRequestDto;
import org.example.app.productservice.dto.ProductResponseDto;
import org.example.app.productservice.model.Product;
import org.example.app.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products) {
           productResponseDtos.add(productService.mapToProductResponseDto(product));
        }
        return ResponseEntity.ok(productResponseDtos);

    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDto productRequestDto) {

        Product product = productService.addProduct(productService.mapToProduct(productRequestDto));
        return ResponseEntity.ok("Product with id " + product.getId() + " created");

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequestDto productRequestDto,
                                                @PathVariable int id) {

        Product product = productService.mapToProduct(productRequestDto);
        product.setId(id);
        Product product1 = productService.updateProduct(product);
        if (product1 != null) {
            return ResponseEntity.ok("Product with id " + product1.getId() + " updated");
        }
        return ResponseEntity.ok("Product with id " + id + " not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

        if(productService.deleteProduct(id)!=null)
            return ResponseEntity.ok("Product with id " + id + " deleted");
        else
            return ResponseEntity.ok("Product with id " + id + " not found");

    }

    @GetMapping
    public ResponseEntity<ProductResponseDto> findProduct(@RequestParam String name) {

       Product product =  productService.searchProduct(name);
       return ResponseEntity.ok(productService.mapToProductResponseDto(product));

    }

}
