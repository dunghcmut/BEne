package com.example.restaurantapi.dto;

import com.example.restaurantapi.entity.TableInfo.Status;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TableInfoDTO {
    private Integer tableID;
    private Status status;
    private String phonenumber;
    private LocalDateTime bookTime;
}