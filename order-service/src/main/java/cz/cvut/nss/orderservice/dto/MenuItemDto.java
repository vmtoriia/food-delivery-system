package cz.cvut.nss.orderservice.dto;

import java.math.BigDecimal;

public record MenuItemDto(
        String id,
        String name,
        BigDecimal price,
        boolean available,
        RestaurantDto restaurant
) {

    public record RestaurantDto(String id) {}
}