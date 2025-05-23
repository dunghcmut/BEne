package com.example.restaurantapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestaurantApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApiApplication.class, args);
    }
}