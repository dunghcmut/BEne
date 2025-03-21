package com.example.restaurantapi.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
// Trigger 20 phut auto delete grok 3
@Service
public class TableStatusService {

    private final EntityManager entityManager;

    public TableStatusService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }







    @Scheduled(fixedRate = 60000) 
@Transactional
public void resetTableStatus() {
    LocalDateTime now = LocalDateTime.now();
    entityManager.createQuery(
        "UPDATE TableInfo t SET t.status = 'EMPTY' " +
        "WHERE t.status = 'BOOKED' AND t.bookTime <= :timeLimit AND t.bookTime < :now")
        .setParameter("timeLimit", now.minusMinutes(20))
        .setParameter("now", now)
        .executeUpdate();
}
}