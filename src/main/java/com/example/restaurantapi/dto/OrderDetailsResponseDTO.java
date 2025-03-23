package com.example.restaurantapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailsResponseDTO {
    private Integer orderId;
    private Integer tableId;
    private List<OrderItemDetailsDTO> items;

    @Data
    public static class OrderItemDetailsDTO {
        private String foodName;
        private Integer quantity;
    }
}