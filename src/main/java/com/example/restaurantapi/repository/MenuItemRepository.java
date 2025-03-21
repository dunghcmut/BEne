package com.example.restaurantapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurantapi.entity.MenuItem;
// JpaRepository interface
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByIsRecommendTrueAndStatus(MenuItem.Status status);
}