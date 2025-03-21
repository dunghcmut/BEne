package com.example.restaurantapi.dto;

import java.time.LocalDateTime;

import com.example.restaurantapi.entity.TableInfo.Status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TableRequestDTO {
    @NotNull(message = "Table ID is required")
    private Integer tableID;

    @NotNull(message = "Status is required")
    private Status status;

    @NotNull(message = "Phone number is required")
    private String phonenumber;

    @NotNull(message = "Book time is required")
    private LocalDateTime bookTime;
}