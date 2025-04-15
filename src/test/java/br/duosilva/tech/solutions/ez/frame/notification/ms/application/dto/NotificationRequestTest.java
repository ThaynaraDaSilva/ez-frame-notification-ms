package br.duosilva.tech.solutions.ez.frame.notification.ms.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationRequestTest {

    @Test
    void shouldCreateNotificationRequestAndAccessFields() {
        // Given
        String videoId = "abc123";
        String status = "COMPLETED";
        String errorMessage = "none";
        String email = "user@example.com";

        // When
        NotificationRequest request = new NotificationRequest(videoId, status, errorMessage, email);

        // Then
        assertEquals(videoId, request.getVideoId());
        assertEquals(status, request.getStatus());
        assertEquals(errorMessage, request.getErrorMessage());
        assertEquals(email, request.getEmail());

        // Update and check setters
        request.setVideoId("xyz456");
        request.setStatus("FAILED");
        request.setErrorMessage("some error");
        request.setEmail("new@example.com");

        assertEquals("xyz456", request.getVideoId());
        assertEquals("FAILED", request.getStatus());
        assertEquals("some error", request.getErrorMessage());
        assertEquals("new@example.com", request.getEmail());
    }
}
