package com.example.restaurantapi.repository;

import com.example.restaurantapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}