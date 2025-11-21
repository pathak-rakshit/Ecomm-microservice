package org.example.app.orderservice.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.app.orderservice.dto.OrderItemDto;
import org.example.app.orderservice.dto.OrderResponseDto;
import org.example.app.orderservice.model.CartItem;
import org.example.app.orderservice.model.Order;
import org.example.app.orderservice.model.OrderItems;
import org.example.app.orderservice.model.OrderStatus;
import org.example.app.orderservice.repository.CartItemRepository;
import org.example.app.orderservice.repository.OrderRepository;
import org.example.app.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
//    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    @Override
    public Optional<OrderResponseDto> createOrder(Long userId) {

//        Optional<User> user = userRepository.findById(Math.toIntExact(userId));
//
//        if (user.isEmpty())
//            return Optional.empty();

        List<CartItem> cartItem = cartItemRepository.findByUserId(String.valueOf(userId));

        //calculating amount
        BigDecimal amount = BigDecimal.ZERO;

        for (CartItem cartItem1 : cartItem) {
            amount = amount.add(cartItem1.getPrice());
        }




        if(cartItem.isEmpty())
            return Optional.empty();

        Order order = new Order();
        order.setUserId(String.valueOf(userId));
        order.setOrderStatus(OrderStatus.SUCCESS);
        order.setTotalAmount(amount);
        //mapping list of cartItems to list of Order items
        List<OrderItems> orderItems = new ArrayList<>();
        for (CartItem cartItem1 : cartItem) {
            OrderItems orderItem = new OrderItems();
            orderItem.setQuantity(cartItem1.getQuantity());
            orderItem.setPrice(cartItem1.getPrice());
            orderItem.setProductId(cartItem1.getProductId());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);


        //convert order into order response dto
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setOrderStatus(OrderStatus.SUCCESS);
        orderResponseDto.setTotalAmount(order.getTotalAmount());
        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        for (OrderItems orderItem : orderItems) {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setOrderId(orderItem.getId());
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderItemDto.setProductId(Long.parseLong(orderItem.getProductId()));
            orderItemDto.setUnitPrice(BigDecimal.valueOf(100));
            orderItemDtos.add(orderItemDto);
        }

        orderResponseDto.setOrderItems(orderItemDtos);

         clearOrder(order.getId());

        return Optional.of(orderResponseDto);





    }

    @Override
    public void clearOrder(Long userId) {


            cartItemRepository.deleteByUserId(String.valueOf(userId));


    }


}
