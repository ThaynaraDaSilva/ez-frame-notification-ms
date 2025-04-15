package br.duosilva.tech.solutions.ez.frame.notification.ms.application.usecases;

import br.duosilva.tech.solutions.ez.frame.notification.ms.application.dto.NotificationRequest;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model.Notification;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class NotificationUsecase {

    private final NotificationService notificationService;

    public NotificationUsecase(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(NotificationRequest request) {
        if (!"failed".equalsIgnoreCase(request.getStatus())) {
            throw new IllegalArgumentException("Notificação só pode ser enviada para falhas. Status: " + request.getStatus());
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("E-mail do destinatário é obrigatório");
        }   

        Notification notification = new Notification(
                request.getVideoId(),
                request.getStatus(),
                request.getErrorMessage(),
                request.getEmail()
        );

        notificationService.sendEmail(notification);
    }
}