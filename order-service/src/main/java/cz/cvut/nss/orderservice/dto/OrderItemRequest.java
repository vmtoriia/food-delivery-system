package cz.cvut.nss.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotBlank String menuItemId,
        @Positive int quantity
) {
}