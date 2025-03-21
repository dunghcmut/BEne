package com.example.restaurantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurantapi.entity.TableInfo;
// JpaRepository interface
@Repository
public interface TableInfoRepository extends JpaRepository<TableInfo, Integer> {
}