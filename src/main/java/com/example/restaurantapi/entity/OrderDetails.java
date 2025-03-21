package com.example.restaurantapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetails")
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailID;

    @ManyToOne
    @JoinColumn(name = "MenuID", nullable = false)
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private MenuOrder menuOrder;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal priceAtOrder;
}