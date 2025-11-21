package org.example.app.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class OrderItemDto {

    private long orderId;
    private long productId;
    private int quantity;
    private BigDecimal unitPrice;
}
