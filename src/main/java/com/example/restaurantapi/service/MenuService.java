package com.example.restaurantapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurantapi.dto.MenuItemDTO;
import com.example.restaurantapi.entity.MenuItem;
import com.example.restaurantapi.repository.MenuItemRepository;
//grok3
@Service
public class MenuService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItemDTO> getTodayMenu() {
        return menuItemRepository.findByIsRecommendTrueAndStatus(MenuItem.Status.available)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MenuItemDTO getMenuItemById(Integer id) {
        return menuItemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Notfound"));
    }

    public MenuItemDTO addMenuItem(MenuItemDTO dto) {
        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setRecommend(dto.isRecommend());
        item.setPrice(dto.getPrice());
        item.setStatus(MenuItem.Status.valueOf(dto.getStatus()));
        item.setLink(dto.getImageUrl());
        MenuItem saved = menuItemRepository.save(item);
        return convertToDTO(saved);
    }
// grok3 
    public MenuItemDTO updateMenuItem(Integer id, MenuItemDTO dto) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notfound"));
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setRecommend(dto.isRecommend());
        item.setPrice(dto.getPrice());
        item.setStatus(MenuItem.Status.valueOf(dto.getStatus()));
        item.setLink(dto.getImageUrl());
        MenuItem updated = menuItemRepository.save(item);
        return convertToDTO(updated);
    }

    public void deleteMenuItem(Integer id) {
        menuItemRepository.deleteById(id);
    }
//grok 3
    private MenuItemDTO convertToDTO(MenuItem item) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(item.getMenuID());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setCategory(item.getCategory());
        dto.setRecommend(item.isRecommend());
        dto.setPrice(item.getPrice());
        dto.setStatus(item.getStatus().name());
        dto.setImageUrl(item.getLink());
        return dto;
    }
}