package com.example.restaurantapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; // Thêm import này

import com.example.restaurantapi.entity.MenuOrder;
import com.example.restaurantapi.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByMenuOrder(MenuOrder menuOrder);
}