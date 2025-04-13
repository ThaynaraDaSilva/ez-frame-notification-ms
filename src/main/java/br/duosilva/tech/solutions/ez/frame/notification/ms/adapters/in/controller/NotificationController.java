package br.duosilva.tech.solutions.ez.frame.notification.ms.adapters.in.controller;

import br.duosilva.tech.solutions.ez.frame.notification.ms.application.usecases.NotificationUsecase;
import br.duosilva.tech.solutions.ez.frame.notification.ms.application.dto.NotificationRequest;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ms/notification")
public class NotificationController {

    private final NotificationUsecase notificationUsecase;

    public NotificationController(NotificationUsecase notificationUsecase) {
        this.notificationUsecase = notificationUsecase;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        Notification notification = new Notification(
                request.getVideoId(),
                request.getStatus(),
                request.getErrorMessage(),
                request.getEmail()
        );
        notificationUsecase.sendNotification(notification);
        return ResponseEntity.ok("Notificação enviada com sucesso");
    }
}