package org.example.app.orderservice.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.app.orderservice.dto.CartItemReqDto;
import org.example.app.orderservice.model.CartItem;
import org.example.app.orderservice.repository.CartItemRepository;
import org.example.app.orderservice.service.CartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;

    @Override
    public boolean addItemToCart(String userId, CartItemReqDto cartItemReqDto) {

//        User user = userRepository.findById(Integer.valueOf(userId)).get();
//
//        if(user == null)
//            return false;
//
//        Product product = productRepository.findById(Math.toIntExact(cartItemReqDto.getProductId())).get();
//
//        if(product == null)
//            return false;
//
//        if(cartItemReqDto.getQuantity() >  product.getStockQuantity())
//           return false;

        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, String.valueOf(cartItemReqDto.getProductId()));
        if(cartItem != null)
        {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemReqDto.getQuantity());
            cartItem.setPrice(cartItem.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        else {
            CartItem newCartItem = new CartItem();
            newCartItem.setUserId(userId);
            newCartItem.setProductId(String.valueOf(cartItemReqDto.getProductId()));
            newCartItem.setQuantity(cartItemReqDto.getQuantity());
            newCartItem.setPrice(BigDecimal.valueOf(10000));
            cartItemRepository.save(newCartItem);
        }
        return true;
    }

    @Override
    public boolean removeItemFromCart(String userId, Long productId) {


        return cartItemRepository.deleteByUserIdAndProductId(userId, String.valueOf(productId));

    }

    @Override
    public List<CartItem> getCart(String userId) {

        return cartItemRepository.findByUserId(userId);

    }
}
