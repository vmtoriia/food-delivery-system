package cz.cvut.nss.notificationservice.dto;

import cz.cvut.nss.notificationservice.entity.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateNotificationRequest(
        @NotBlank String recipientId,
        @NotNull NotificationType type,
        @NotBlank @Size(max = 1000) String message
) {
}