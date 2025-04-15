package br.duosilva.tech.solutions.ez.frame.notification.ms.infrastructure.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AmazonProperties.class)
@EnableConfigurationProperties(AmazonProperties.class)
@TestPropertySource(properties = {
        "aws.region=us-east-1",
        "aws.credentials.access-key=test-access-key",
        "aws.credentials.secret-key=test-secret-key"
})
class AmazonPropertiesTest {

    @Autowired
    private AmazonProperties amazonProperties;

    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @Test
    void configurationProperties_shouldBindCorrectly() {
        assertNotNull(amazonProperties, "AmazonProperties should be initialized");
        assertEquals("us-east-1", amazonProperties.getRegion(), "Region should match configured value");
        assertNotNull(amazonProperties.getCredentials(), "Credentials should not be null");
        assertEquals("test-access-key", amazonProperties.getCredentials().getAccessKey(), "Access key should match configured value");
        assertEquals("test-secret-key", amazonProperties.getCredentials().getSecretKey(), "Secret key should match configured value");
    }

    @Test
    void amazonSimpleEmailServiceBean_shouldBeConfiguredCorrectly() {
        assertNotNull(amazonSimpleEmailService, "AmazonSimpleEmailService bean should be created");
    }

    @Test
    void credentialsGettersAndSetters_shouldWorkCorrectly() {
        AmazonProperties.Credentials credentials = new AmazonProperties.Credentials();
        String accessKey = "new-access-key";
        String secretKey = "new-secret-key";

        credentials.setAccessKey(accessKey);
        credentials.setSecretKey(secretKey);

        assertEquals(accessKey, credentials.getAccessKey(), "Access key should match set value");
        assertEquals(secretKey, credentials.getSecretKey(), "Secret key should match set value");
    }
}