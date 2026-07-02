package cz.cvut.nss.deliveryservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCourierRequest(
        @NotBlank String name
) {
}