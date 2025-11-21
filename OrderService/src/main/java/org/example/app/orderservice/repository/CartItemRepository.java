package org.example.app.orderservice.repository;

import org.example.app.orderservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByUserIdAndProductId(String userId, String product);

    boolean deleteByUserIdAndProductId(String userId, String product);

    List<CartItem> findByUserId(String userId);

    void deleteByUserId(String userId);
}
