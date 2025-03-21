package com.example.restaurantapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurantapi.dto.MenuItemDTO;
import com.example.restaurantapi.dto.TableInfoDTO;
import com.example.restaurantapi.dto.TableRequestDTO;
import com.example.restaurantapi.service.MenuService;
import com.example.restaurantapi.service.TableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private TableService tableService;
    @GetMapping("/tables")
    public List<TableInfoDTO> getAllTables() {
        return tableService.getAllTables();
    }
    @PostMapping("/menu")
    public MenuItemDTO addMenuItem(@RequestBody MenuItemDTO dto) {
        return menuService.addMenuItem(dto);
    }

    @PutMapping("/menu/{id}")
    public MenuItemDTO updateMenuItem(@PathVariable Integer id, @RequestBody MenuItemDTO dto) {
        return menuService.updateMenuItem(id, dto);
    }

    @DeleteMapping("/menu/{id}")
    public void deleteMenuItem(@PathVariable Integer id) {
        menuService.deleteMenuItem(id);
    }

    // API cho TableInfo với DTO riêng grok3
    @PostMapping("/table")
    public TableInfoDTO addTable(@Valid @RequestBody TableRequestDTO dto) {
        return tableService.addTable(dto);
    }

    @PutMapping("/table/{id}")
    public TableInfoDTO updateTable(@PathVariable Integer id, @Valid @RequestBody TableRequestDTO dto) {
        return tableService.updateTable(id, dto);
    }

    @DeleteMapping("/table/{id}")
    public void deleteTable(@PathVariable Integer id) {
        tableService.deleteTable(id);
    }
}