package br.duosilva.tech.solutions.ez.frame.notification.ms.frameworks.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleIllegalArgumentException_shouldReturnBadRequestWithExceptionMessage() {
        String errorMessage = "Invalid input provided";
        IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

        ResponseEntity<String> response = exceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD_REQUEST");
        assertEquals(errorMessage, response.getBody(), "Response body should contain the exception message");
    }

    @Test
    void handleGenericException_shouldReturnInternalServerErrorWithFormattedMessage() {
        String errorMessage = "Unexpected error";
        Exception exception = new Exception(errorMessage);
        String expectedBody = "Erro interno no servidor: " + errorMessage;

        ResponseEntity<String> response = exceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Status should be INTERNAL_SERVER_ERROR");
        assertEquals(expectedBody, response.getBody(), "Response body should contain the formatted error message");
    }

    @Test
    void handleIllegalArgumentException_withNullMessage_shouldReturnBadRequestWithNullBody() {
        IllegalArgumentException exception = new IllegalArgumentException((String) null);

        ResponseEntity<String> response = exceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD_REQUEST");
        assertNull(response.getBody(), "Response body should be null when exception message is null");
    }

    @Test
    void handleGenericException_withNullMessage_shouldReturnInternalServerErrorWithFormattedNullMessage() {
        Exception exception = new Exception((String) null);
        String expectedBody = "Erro interno no servidor: null";

        ResponseEntity<String> response = exceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Status should be INTERNAL_SERVER_ERROR");
        assertEquals(expectedBody, response.getBody(), "Response body should contain the formatted null message");
    }
}