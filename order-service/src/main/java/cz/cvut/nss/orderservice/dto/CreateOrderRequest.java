package cz.cvut.nss.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderRequest(
        @NotBlank String customerId,
        @NotBlank String restaurantId,
        @NotEmpty List<OrderItemRequest> items
) {
}