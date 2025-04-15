package br.duosilva.tech.solutions.ez.frame.notification.ms.adapters.out;

import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model.Notification;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SesNotificationAdapterTest {

    private AmazonSimpleEmailService sesClient;
    private SesNotificationAdapter adapter;

    @BeforeEach
    void setUp() {
        sesClient = mock(AmazonSimpleEmailService.class);
        adapter = new SesNotificationAdapter(sesClient);
    }

    @Test
    void sendEmail_shouldCallSesClientWithCorrectRequest() {
        Notification notification = new Notification(
                "video123",
                "FAILED",
                "Erro de transcodificação",
                "user@example.com"
        );

        when(sesClient.sendEmail(any(SendEmailRequest.class)))
                .thenReturn(new SendEmailResult().withMessageId("msg-123"));

        adapter.sendEmail(notification);

        ArgumentCaptor<SendEmailRequest> captor = ArgumentCaptor.forClass(SendEmailRequest.class);
        verify(sesClient, times(1)).sendEmail(captor.capture());

        SendEmailRequest request = captor.getValue();

        assertEquals("user@example.com", request.getSource());
        assertEquals("user@example.com", request.getDestination().getToAddresses().get(0));
        assertTrue(request.getMessage().getSubject().getData().contains("Falha"));
        assertTrue(request.getMessage().getBody().getText().getData().contains("Erro de transcodificação"));
    }

    @Test
    void sendEmail_whenSesThrowsException_shouldThrowRuntimeException() {
        Notification notification = new Notification(
                "video456",
                "FAILED",
                "Erro desconhecido",
                "admin@example.com"
        );

        when(sesClient.sendEmail(any(SendEmailRequest.class)))
                .thenThrow(new RuntimeException("SES indisponível"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adapter.sendEmail(notification);
        });

        assertTrue(exception.getMessage().contains("Falha ao enviar e-mail"));
        verify(sesClient, times(1)).sendEmail(any(SendEmailRequest.class));
    }
}
