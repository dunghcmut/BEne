package com.example.restaurantapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "MenuItem")
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuID;

    @Column(nullable = false)
    private String name;

    private String description;

    private String category;

    private boolean isRecommend = false;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private String link;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Status {
        available, unavailable
    }
}