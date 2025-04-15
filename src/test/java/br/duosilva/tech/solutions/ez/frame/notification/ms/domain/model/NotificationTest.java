package br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void constructor_shouldInitializeFieldsCorrectly() {
        String videoId = "video123";
        String status = "SUCCESS";
        String errorMessage = null;
        String email = "user@test.com";

        Notification notification = new Notification(videoId, status, errorMessage, email);

        assertEquals(videoId, notification.getVideoId(), "VideoId should match constructor input");
        assertEquals(status, notification.getStatus(), "Status should match constructor input");
        assertEquals(errorMessage, notification.getErrorMessage(), "ErrorMessage should match constructor input");
        assertEquals(email, notification.getEmail(), "Email should match constructor input");
    }

    @Test
    void setters_shouldUpdateFieldsCorrectly() {
        Notification notification = new Notification("initialId", "INITIAL", "Initial error", "initial@test.com");

        String newVideoId = "newVideo456";
        String newStatus = "FAILED";
        String newErrorMessage = "Something went wrong";
        String newEmail = "newuser@test.com";

        notification.setVideoId(newVideoId);
        notification.setStatus(newStatus);
        notification.setErrorMessage(newErrorMessage);
        notification.setEmail(newEmail);

        assertEquals(newVideoId, notification.getVideoId(), "VideoId should be updated by setter");
        assertEquals(newStatus, notification.getStatus(), "Status should be updated by setter");
        assertEquals(newErrorMessage, notification.getErrorMessage(), "ErrorMessage should be updated by setter");
        assertEquals(newEmail, notification.getEmail(), "Email should be updated by setter");
    }

    @Test
    void getters_shouldReturnNullWhenFieldsAreNull() {
        Notification notification = new Notification(null, null, null, null);

        assertNull(notification.getVideoId(), "VideoId should be null");
        assertNull(notification.getStatus(), "Status should be null");
        assertNull(notification.getErrorMessage(), "ErrorMessage should be null");
        assertNull(notification.getEmail(), "Email should be null");
    }
}