package com.example.restaurantapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurantapi.dto.TableInfoDTO;
import com.example.restaurantapi.dto.TableRequestDTO;
import com.example.restaurantapi.entity.TableInfo;
import com.example.restaurantapi.repository.TableInfoRepository;

@Service
public class TableService {

    @Autowired
    private TableInfoRepository tableInfoRepository;

    // Getall Tables
    public List<TableInfoDTO> getAllTables() {
        List<TableInfo> tables = tableInfoRepository.findAll();
        return tables.stream().map(table -> {
            TableInfoDTO dto = new TableInfoDTO();
            dto.setTableID(table.getTableID());
            dto.setStatus(table.getStatus());
            dto.setPhonenumber(table.getPhonenumber());
            dto.setBookTime(table.getBookTime());
            return dto;
        }).collect(Collectors.toList());
    }
//  Them ban
    @Transactional
    public TableInfoDTO addTable(TableRequestDTO dto) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableID(dto.getTableID());
        tableInfo.setStatus(dto.getStatus());
        tableInfo.setPhonenumber(dto.getPhonenumber());
        tableInfo.setBookTime(dto.getBookTime());
        
        TableInfo savedTable = tableInfoRepository.save(tableInfo);
        
        TableInfoDTO result = new TableInfoDTO();
        result.setTableID(savedTable.getTableID());
        result.setStatus(savedTable.getStatus());
        result.setPhonenumber(savedTable.getPhonenumber());
        result.setBookTime(savedTable.getBookTime());
        
        return result;
    }

    @Transactional
    public TableInfoDTO updateTable(Integer id, TableRequestDTO dto) {
        TableInfo tableInfo = tableInfoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notfound " + id));
        
        tableInfo.setStatus(dto.getStatus());
        tableInfo.setPhonenumber(dto.getPhonenumber());
        tableInfo.setBookTime(dto.getBookTime());
        
        TableInfo updatedTable = tableInfoRepository.save(tableInfo);
        
        TableInfoDTO result = new TableInfoDTO();
        result.setTableID(updatedTable.getTableID());
        result.setStatus(updatedTable.getStatus());
        result.setPhonenumber(updatedTable.getPhonenumber());
        result.setBookTime(updatedTable.getBookTime());
        
        return result;
    }

    @Transactional
    public void deleteTable(Integer id) {
        TableInfo tableInfo = tableInfoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notfound " + id));
        tableInfoRepository.delete(tableInfo);
    }
}