package com.example.restaurantapi.controller;

import java.util.List;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.restaurantapi.dto.MenuItemDTO;
import com.example.restaurantapi.dto.TableInfoDTO;
import com.example.restaurantapi.dto.TableRequestDTO;
import com.example.restaurantapi.service.MenuService;
import com.example.restaurantapi.service.TableService;

import jakarta.validation.Valid;
import com.fasterxml.jackson.databind.ObjectMapper; // Thêm import này

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private TableService tableService;

    @Autowired
    private ObjectMapper objectMapper; // Thêm dependency injection cho ObjectMapper

    @GetMapping("/tables")
    public List<TableInfoDTO> getAllTables() {
        return tableService.getAllTables();
    }

    @PostMapping(value = "/menu", consumes = {"multipart/form-data"})
    public MenuItemDTO addMenuItem(
            @RequestPart("menuItem") String menuItemJson, // Nhận chuỗi JSON thay vì DTO
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        try {
            // Parse chuỗi JSON thành MenuItemDTO
            MenuItemDTO dto = objectMapper.readValue(menuItemJson, MenuItemDTO.class);
            return menuService.addMenuItem(dto, image);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new IOException("Invalid JSON format for menuItem", e);
        }
    }

    @PutMapping("/menu/{id}")
    public MenuItemDTO updateMenuItem(@PathVariable Integer id, @RequestBody MenuItemDTO dto) {
        return menuService.updateMenuItem(id, dto);
    }

    @DeleteMapping("/menu/{id}")
    public void deleteMenuItem(@PathVariable Integer id) {
        menuService.deleteMenuItem(id);
    }

    // API cho TableInfo với DTO riêng
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