package br.duosilva.tech.solutions.ez.frame.notification.ms.adapters.in.controller;

import br.duosilva.tech.solutions.ez.frame.notification.ms.application.dto.NotificationRequest;
import br.duosilva.tech.solutions.ez.frame.notification.ms.application.usecases.NotificationUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private NotificationUsecase notificationUsecase;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendNotification_shouldCallUsecaseAndReturnSuccessResponse() {
        NotificationRequest request = new NotificationRequest(
                "video123",
                "FAILED",
                "Error",
                "user@test.com"
        );

        ResponseEntity<String> response = notificationController.sendNotification(request);

        verify(notificationUsecase, times(1)).sendNotification(request);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Notificação enviada com sucesso", response.getBody());
    }

    @Test
    void sendNotification_whenUsecaseThrowsException_shouldThrowException() {
        NotificationRequest request = new NotificationRequest(
                "video123",
                "FAILED",
                "Error",
                "user@test.com"
        );

        doThrow(new RuntimeException("Usecase failure"))
                .when(notificationUsecase).sendNotification(request);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            notificationController.sendNotification(request);
        });

        assertEquals("Usecase failure", exception.getMessage());
    }
}