package org.example.app.orderservice.service;

import org.example.app.orderservice.dto.CartItemReqDto;
import org.example.app.orderservice.model.CartItem;

import java.util.List;

public interface CartService
{
    public boolean addItemToCart(String userId, CartItemReqDto cartItemReqDto);
    public boolean removeItemFromCart(String userId, Long productId);

    List<CartItem> getCart(String userId);
}
