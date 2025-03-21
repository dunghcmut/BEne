package com.example.restaurantapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurantapi.entity.MenuOrder;
// JpaRepository interface
public interface MenuOrderRepository extends JpaRepository<MenuOrder, Integer> {
    List<MenuOrder> findByStatus(MenuOrder.Status status);
}