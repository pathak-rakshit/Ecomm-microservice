package org.example.app.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.app.orderservice.dto.CartItemReqDto;
import org.example.app.orderservice.model.CartItem;
import org.example.app.orderservice.repository.CartItemRepository;
import org.example.app.orderservice.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartService cartService;

    private final CartItemRepository cartItemRepository;

    @PostMapping
    public ResponseEntity<String> addCartItemToCart(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody CartItemReqDto cartItemReqDto
    )
    {
        if(!cartService.addItemToCart(userId, cartItemReqDto)){
            return ResponseEntity.badRequest().body("cannot add product");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteCartItemFromCart(@RequestHeader("X-User-Id") String userId,
                                                         @PathVariable Long productId)
    {
        if(cartService.removeItemFromCart(userId, productId))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(
            @RequestHeader("X-User-Id") String userId
    )
    {
        return ResponseEntity.ok(cartService.getCart(userId));
    }
}
