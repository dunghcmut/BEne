package com.example.restaurantapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TableInfo")
@Data
public class TableInfo {
    @Id 
    private Integer tableID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private String phonenumber;
    @Column(nullable = true)
    private LocalDateTime bookTime;
    public enum Status {
        EMPTY, USING, BOOKED
    }
}