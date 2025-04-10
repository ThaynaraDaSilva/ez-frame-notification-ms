//package br.duosilva.tech.solutions.ez.frame.notification.ms.infrastructure.config;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConfigurationProperties(prefix = "aws")
//public class AwsConfig {
//
//    private String region;
//    private Credentials credentials;
//
//    public static class Credentials {
//        private String accessKey;
//        private String secretKey;
//
//        public String getAccessKey() {
//            return accessKey;
//        }
//
//        public void setAccessKey(String accessKey) {
//            this.accessKey = accessKey;
//        }
//
//        public String getSecretKey() {
//            return secretKey;
//        }
//
//        public void setSecretKey(String secretKey) {
//            this.secretKey = secretKey;
//        }
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public void setRegion(String region) {
//        this.region = region;
//    }
//
//    public Credentials getCredentials() {
//        return credentials;
//    }
//
//    public void setCredentials(Credentials credentials) {
//        this.credentials = credentials;
//    }
//
//    @Bean
//    public AmazonSimpleEmailService amazonSimpleEmailService() {
//        // Obtém as credenciais e a região diretamente dos campos da classe
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
//                credentials.getAccessKey(),
//                credentials.getSecretKey()
//        );
//
//        return AmazonSimpleEmailServiceClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withRegion(region)
//                .build();
//    }
//}