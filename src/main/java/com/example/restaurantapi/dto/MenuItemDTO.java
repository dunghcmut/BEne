package com.example.restaurantapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemDTO {
    private Integer id;
    private String name;
    private String description;
    private String category;
    private boolean isRecommend = true;
    private BigDecimal price;
    private String status;
    private String imageUrl;
}