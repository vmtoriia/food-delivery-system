package cz.cvut.nss.catalogservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRestaurantRequest(
        @NotBlank String name,
        String category,
        Double rating
) {
}