package com.example.restaurantapi.dto;

import java.util.List;

import lombok.Data;
//grok3
@Data
public class OrderRequestDTO {
    private Integer tableNumber;
    private List<OrderItemDTO> items;

    @Data
    public static class OrderItemDTO {
        private Integer foodId;
        private Integer quantity;
    }
}