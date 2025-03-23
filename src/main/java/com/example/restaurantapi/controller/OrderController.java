package com.example.restaurantapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurantapi.dto.OrderDetailsResponseDTO;
import com.example.restaurantapi.dto.OrderRequestDTO;
import com.example.restaurantapi.dto.OrderResponseDTO;
import com.example.restaurantapi.dto.UpdateOrderStatusDTO;
import com.example.restaurantapi.service.OrderService;

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

    @GetMapping("/orders/PENDING")
    public ResponseEntity<List<OrderDetailsResponseDTO>> getPendingOrders() {
        List<OrderDetailsResponseDTO> PENDINGOrders = orderService.getPendingOrders();
        return ResponseEntity.ok(PENDINGOrders);
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
        @PathVariable Integer orderId,
        @RequestBody UpdateOrderStatusDTO dto) {
        OrderResponseDTO response = orderService.updateOrderStatus(orderId, dto);
        return ResponseEntity.ok(response);
    }
}