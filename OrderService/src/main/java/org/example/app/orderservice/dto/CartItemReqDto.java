package org.example.app.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemReqDto {
    private Long productId;
    private int quantity;
}
