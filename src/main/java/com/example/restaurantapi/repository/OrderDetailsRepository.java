package com.example.restaurantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurantapi.entity.OrderDetails;
// JpaRepository interface
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}