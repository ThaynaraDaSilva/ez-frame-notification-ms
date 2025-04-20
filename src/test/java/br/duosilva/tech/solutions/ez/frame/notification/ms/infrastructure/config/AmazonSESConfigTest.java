package br.duosilva.tech.solutions.ez.frame.notification.ms.infrastructure.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;

@ExtendWith(MockitoExtension.class)
class AmazonSESConfigTest {

    @InjectMocks
    private AmazonSESConfig amazonSESConfig;

    @Test
    void shouldCreateAmazonSimpleEmailServiceClient() {
        ReflectionTestUtils.setField(amazonSESConfig, "accessKey", "test-access-key");
        ReflectionTestUtils.setField(amazonSESConfig, "secretKey", "test-secret-key");
        ReflectionTestUtils.setField(amazonSESConfig, "region", "us-east-1");

        AmazonSimpleEmailService sesClient = amazonSESConfig.amazonSimpleEmailService();
        assertNotNull(sesClient);
    }
}