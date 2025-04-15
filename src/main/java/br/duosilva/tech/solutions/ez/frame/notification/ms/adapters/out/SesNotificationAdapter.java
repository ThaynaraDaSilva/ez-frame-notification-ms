package br.duosilva.tech.solutions.ez.frame.notification.ms.adapters.out;

import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.model.Notification;
import br.duosilva.tech.solutions.ez.frame.notification.ms.domain.service.NotificationService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SesNotificationAdapter implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(SesNotificationAdapter.class);
    private final AmazonSimpleEmailService sesClient;

    public SesNotificationAdapter(AmazonSimpleEmailService sesClient) {
        this.sesClient = sesClient;
    }

    public void sendEmail(Notification notification) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withSource(notification.getEmail())
                    .withDestination(new Destination().withToAddresses(notification.getEmail()))
                    .withMessage(new Message()
                            .withSubject(new Content("Falha no Processamento do Vídeo"))
                            .withBody(new Body()
                                    .withText(new Content(
                                            "Vídeo " + notification.getVideoId() + " falhou ao ser processado. Detalhes: " + notification.getErrorMessage()))));
            sesClient.sendEmail(request);
            logger.info("E-mail enviado para {} sobre falha no vídeo {}", notification.getEmail(), notification.getVideoId());
        } catch (Exception e) {
            logger.error("Erro ao enviar e-mail para {}: {}", notification.getEmail(), e.getMessage());
            throw new RuntimeException("Falha ao enviar e-mail", e);
        }
    }
}
