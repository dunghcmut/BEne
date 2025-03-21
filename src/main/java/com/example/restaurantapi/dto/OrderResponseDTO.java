package com.example.restaurantapi.dto;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private String message;
    private Integer orderId;
}