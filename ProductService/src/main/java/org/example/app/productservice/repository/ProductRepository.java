package org.example.app.productservice.repository;

import org.example.app.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByAvailableTrue();

    @Query(value = "select * from product where name like %:productName%", nativeQuery = true)
    Product searchByName(@Param("productName") String productName);
}
