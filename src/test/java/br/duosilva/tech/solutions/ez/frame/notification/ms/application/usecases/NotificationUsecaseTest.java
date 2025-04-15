package br.duosilva.tech.solutions.ez.frame.notification.ms.application.usecases;

import br.duosilva.tech.solutions.ez.frame.notification.ms.application.dto.NotificationRequest;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model.Notification;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationUsecaseTest {

    private NotificationService notificationService;
    private NotificationUsecase notificationUsecase;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        notificationUsecase = new NotificationUsecase(notificationService);
    }

    @Test
    void shouldSendNotificationWhenStatusIsFailedAndEmailIsPresent() {
        // Given
        NotificationRequest request = new NotificationRequest(
                "video123", "FAILED", "Some error", "user@example.com"
        );

        // When
        notificationUsecase.sendNotification(request);

        // Then
        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationService, times(1)).sendEmail(captor.capture());

        Notification sentNotification = captor.getValue();
        assertEquals("video123", sentNotification.getVideoId());
        assertEquals("FAILED", sentNotification.getStatus());
        assertEquals("Some error", sentNotification.getErrorMessage());
        assertEquals("user@example.com", sentNotification.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenStatusIsNotFailed() {
        NotificationRequest request = new NotificationRequest(
                "video123", "COMPLETED", "No error", "user@example.com"
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> notificationUsecase.sendNotification(request));

        assertTrue(exception.getMessage().contains("Status: COMPLETED"));
        verifyNoInteractions(notificationService);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        NotificationRequest request = new NotificationRequest(
                "video123", "FAILED", "Some error", null
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> notificationUsecase.sendNotification(request));

        assertEquals("E-mail do destinatário é obrigatório", exception.getMessage());
        verifyNoInteractions(notificationService);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        NotificationRequest request = new NotificationRequest(
                "video123", "FAILED", "Some error", ""
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> notificationUsecase.sendNotification(request));

        assertEquals("E-mail do destinatário é obrigatório", exception.getMessage());
        verifyNoInteractions(notificationService);
    }
}
