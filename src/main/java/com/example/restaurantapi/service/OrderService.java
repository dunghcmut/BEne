package com.example.restaurantapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurantapi.dto.OrderRequestDTO;
import com.example.restaurantapi.dto.OrderResponseDTO;
import com.example.restaurantapi.dto.UpdateOrderStatusDTO;
import com.example.restaurantapi.entity.MenuItem;
import com.example.restaurantapi.entity.MenuOrder;
import com.example.restaurantapi.entity.OrderDetails;
import com.example.restaurantapi.repository.MenuItemRepository;
import com.example.restaurantapi.repository.MenuOrderRepository;
import com.example.restaurantapi.repository.OrderDetailsRepository;

@Service
public class OrderService {
    @Autowired
    private MenuOrderRepository menuOrderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Transactional
    public OrderResponseDTO confirmOrder(OrderRequestDTO request) {
        MenuOrder order = new MenuOrder();
        order.setTableID(request.getTableNumber());
        order.setStatus(MenuOrder.Status.pending);
        MenuOrder savedOrder = menuOrderRepository.save(order);

        for (OrderRequestDTO.OrderItemDTO itemDTO : request.getItems()) {
            // Grok3 
            MenuItem menuItem = menuItemRepository.findById(itemDTO.getFoodId())
                    .orElseThrow(() -> new RuntimeException("Notfound"));
            OrderDetails details = new OrderDetails();
            details.setMenuItem(menuItem);
            details.setMenuOrder(savedOrder);
            details.setQuantity(itemDTO.getQuantity());
            details.setPriceAtOrder(menuItem.getPrice());
            orderDetailsRepository.save(details);
        }

        OrderResponseDTO response = new OrderResponseDTO();
        response.setMessage("Complete");
        response.setOrderId(savedOrder.getOrderID());
        return response;
    }

    public List<MenuOrder> getPendingOrders() {
        return menuOrderRepository.findByStatus(MenuOrder.Status.pending);
    }

    public OrderResponseDTO updateOrderStatus(Integer orderId, UpdateOrderStatusDTO dto) {
        MenuOrder order = menuOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        MenuOrder.Status newStatus = MenuOrder.Status.valueOf(dto.getStatus().toUpperCase());
        order.setStatus(newStatus);
        menuOrderRepository.save(order);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setMessage("Order status updated");
        response.setOrderId(orderId);
        return response;
    }
}