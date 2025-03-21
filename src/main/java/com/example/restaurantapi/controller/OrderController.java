package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.OrderRequestDTO;
import com.example.restaurantapi.dto.OrderResponseDTO;
import com.example.restaurantapi.dto.UpdateOrderStatusDTO;
import com.example.restaurantapi.entity.MenuOrder;
import com.example.restaurantapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/confirm")
    public ResponseEntity<OrderResponseDTO> confirmOrder(@RequestBody OrderRequestDTO request) {
        OrderResponseDTO response = orderService.confirmOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders/pending")
    public ResponseEntity<List<MenuOrder>> getPendingOrders() {
        List<MenuOrder> pendingOrders = orderService.getPendingOrders();
        return ResponseEntity.ok(pendingOrders);
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
        @PathVariable Integer orderId,
        @RequestBody UpdateOrderStatusDTO dto) {
        OrderResponseDTO response = orderService.updateOrderStatus(orderId, dto);
        return ResponseEntity.ok(response);
    }
}