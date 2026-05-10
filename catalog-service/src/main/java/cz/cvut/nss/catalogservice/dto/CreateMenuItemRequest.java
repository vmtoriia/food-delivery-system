package cz.cvut.nss.catalogservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateMenuItemRequest(
        @NotBlank String name,
        String category,
        @NotNull @Positive BigDecimal price,
        boolean available
) {
}