package cz.cvut.nss.notificationservice.messaging;

import cz.cvut.nss.notificationservice.dto.CreateNotificationRequest;
import cz.cvut.nss.notificationservice.entity.NotificationType;
import cz.cvut.nss.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void handleOrderEvents(String message) {
        log.info("Received Order event from Kafka: {}", message);

        CreateNotificationRequest request = new CreateNotificationRequest(
                "restaurant-system",
                NotificationType.ORDER_PLACED,
                "New order update received: " + message
        );

        notificationService.create(request);
        log.info("Order notification saved to database.");
    }

    @KafkaListener(topics = "delivery-events", groupId = "notification-group")
    public void handleDeliveryEvents(String message) {
        log.info("Received Delivery event from Kafka: {}", message);

        CreateNotificationRequest request = new CreateNotificationRequest(
                "customer-system",
                NotificationType.DELIVERY_STATUS_CHANGED,
                "Delivery status updated: " + message
        );

        notificationService.create(request);
        log.info("Delivery notification saved to database.");
    }
}