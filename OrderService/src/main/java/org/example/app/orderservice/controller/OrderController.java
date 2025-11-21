package org.example.app.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.app.orderservice.dto.OrderResponseDto;
import org.example.app.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestHeader("X-USER-ID") Long id)
    {
          Optional<OrderResponseDto> orderResponseDto = orderService.createOrder(id);
          if(orderResponseDto.isEmpty())
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          else
              return new ResponseEntity<>(orderResponseDto.get(), HttpStatus.CREATED);

    }
}
