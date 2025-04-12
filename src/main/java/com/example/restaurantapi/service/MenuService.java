package com.example.restaurantapi.service;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.restaurantapi.dto.MenuItemDTO;
import com.example.restaurantapi.entity.MenuItem;
import com.example.restaurantapi.repository.MenuItemRepository;

@Service
public class MenuService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Value("${custom.image-folder}")
    private String uploadDir;

    public List<MenuItemDTO> getTodayMenu() {
        return menuItemRepository.findByIsRecommendTrueAndStatus(MenuItem.Status.available)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MenuItemDTO getMenuItemById(Integer id) {
        return menuItemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Menu item with ID " + id + " not found"));
    }

    public MenuItemDTO addMenuItem(MenuItemDTO dto, MultipartFile image) throws IOException {
        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setRecommend(dto.isRecommend());
        item.setPrice(dto.getPrice());
        item.setStatus(MenuItem.Status.valueOf(dto.getStatus()));

        if (image != null && !image.isEmpty()) {
            String imageFileName = saveImage(image);
            String imageUrl = "/images/" + imageFileName;
            item.setLink(imageUrl);
        } else {
            item.setLink(dto.getImageUrl());
        }

        MenuItem saved = menuItemRepository.save(item);
        return convertToDTO(saved);
    }

    public MenuItemDTO updateMenuItem(Integer id, MenuItemDTO dto) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item with ID " + id + " not found"));
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

    private String saveImage(MultipartFile image) throws IOException {
        if (uploadDir == null || uploadDir.isEmpty()) {
            throw new IllegalStateException("Upload directory is not configured.");
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName = image.getOriginalFilename();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String newFileName = UUID.randomUUID().toString() + fileExtension;
        Path filePath = uploadPath.resolve(newFileName);
        Files.write(filePath, image.getBytes());

        return newFileName;
    }
}
