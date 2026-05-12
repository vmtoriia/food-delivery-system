package cz.cvut.nss.notificationservice.service;

import cz.cvut.nss.notificationservice.dto.CreateNotificationRequest;
import cz.cvut.nss.notificationservice.entity.Notification;
import cz.cvut.nss.notificationservice.entity.NotificationStatus;
import cz.cvut.nss.notificationservice.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Notification findById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Notification> findByRecipient(String recipientId) {
        return notificationRepository.findByRecipientIdOrderByCreatedAtDesc(recipientId);
    }

    public Notification create(CreateNotificationRequest request) {
        Notification notification = Notification.builder()
                .recipientId(request.recipientId())
                .type(request.type())
                .message(request.message())
                .build();
        return notificationRepository.save(notification);
    }

    public Notification markAsSent(String id) {
        Notification notification = findById(id);
        notification.setStatus(NotificationStatus.SENT);
        return notificationRepository.save(notification);
    }

    public void delete(String id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found: " + id);
        }
        notificationRepository.deleteById(id);
    }

}
