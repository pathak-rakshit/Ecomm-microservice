package org.example.app.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.app.orderservice.model.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

public class OrderResponseDto {

    private long orderId;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private List<OrderItemDto> orderItems = new ArrayList<>();


}
