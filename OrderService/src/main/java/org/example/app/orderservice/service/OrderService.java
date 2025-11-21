package org.example.app.orderservice.service;

import org.example.app.orderservice.dto.OrderResponseDto;

import java.util.Optional;

public interface OrderService {

    public Optional<OrderResponseDto> createOrder(Long userId);

    public void clearOrder(Long userId);
}
