package br.duosilva.tech.solutions.ez.frame.notification.ms.domain.service;

import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model.Notification;

public interface NotificationService {
    void sendNotification(Notification notification);
}
