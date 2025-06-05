package com.example.restaurantapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.restaurantapi.dto.OrderResponseDTO;
import com.example.restaurantapi.dto.UpdateOrderStatusDTO;
import com.example.restaurantapi.entity.MenuOrder;
import com.example.restaurantapi.repository.MenuOrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private MenuOrderRepository menuOrderRepository;

    @InjectMocks
    private OrderService orderService;

    private MenuOrder existingOrder;

    @BeforeEach
    void setUp() {
        existingOrder = new MenuOrder();
        existingOrder.setOrderID(1);
        existingOrder.setStatus(MenuOrder.Status.PENDING);
    }

    @Test
    void updateOrderStatus_convertsStatusStringAndSaves() {
        UpdateOrderStatusDTO dto = new UpdateOrderStatusDTO();
        dto.setStatus("complete");

        when(menuOrderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
        when(menuOrderRepository.save(any(MenuOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderResponseDTO response = orderService.updateOrderStatus(1, dto);

        assertEquals(1, response.getOrderId());
        assertEquals(MenuOrder.Status.complete, existingOrder.getStatus());
        verify(menuOrderRepository).save(existingOrder);
    }
}
