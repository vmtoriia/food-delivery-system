package cz.cvut.nss.notificationservice.controller;

import cz.cvut.nss.notificationservice.dto.CreateNotificationRequest;
import cz.cvut.nss.notificationservice.entity.Notification;
import cz.cvut.nss.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<Notification> findAll() {
        return notificationService.findAll();
    }

    @GetMapping("/{id}")
    public Notification findById(@PathVariable String id) {
        return notificationService.findById(id);
    }

    @GetMapping("/recipient/{recipientId}")
    public List<Notification> findByRecipient(@PathVariable String recipientId) {
        return notificationService.findByRecipient(recipientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@Valid @RequestBody CreateNotificationRequest request) {
        return notificationService.create(request);
    }

    @PutMapping("/{id}/send")
    public Notification markAsSent(@PathVariable String id) {
        return notificationService.markAsSent(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        notificationService.delete(id);
    }
}