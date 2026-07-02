package cz.cvut.nss.userservice.dto;

import cz.cvut.nss.userservice.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCustomerRequest(
        @NotBlank @Email String email,
        @NotBlank String name,
        @NotNull Role role
) {
}