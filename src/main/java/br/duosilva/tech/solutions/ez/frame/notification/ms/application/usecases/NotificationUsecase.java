package br.duosilva.tech.solutions.ez.frame.notification.ms.application.usecases;

import br.duosilva.tech.solutions.ez.frame.notification.ms.adapters.out.SesEmailAdapter;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model.Notification;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationUsecase implements NotificationService {
    private final SesEmailAdapter sesEmailAdapter;

    public NotificationUsecase(SesEmailAdapter sesEmailAdapter) {
        this.sesEmailAdapter = sesEmailAdapter;
    }

    @Override
    public void sendNotification(Notification notification) {
        if (!"failed".equals(notification.getStatus())) {
            throw new IllegalArgumentException("Notificação só pode ser enviada para falhas. Status: " + notification.getStatus());
        }
        if (notification.getEmail() == null || notification.getEmail().isEmpty()) {
            throw new IllegalArgumentException("E-mail do destinatário é obrigatório");
        }
        sesEmailAdapter.sendEmail(notification);
    }
}
