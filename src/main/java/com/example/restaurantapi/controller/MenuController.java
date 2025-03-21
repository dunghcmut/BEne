package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.MenuItemDTO;
import com.example.restaurantapi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/today")
    public List<MenuItemDTO> getTodayMenu() {
        return menuService.getTodayMenu();
    }

    @GetMapping("/menu/{id}")
    public MenuItemDTO getMenuItemById(@PathVariable Integer id) {
        return menuService.getMenuItemById(id);
    }
}