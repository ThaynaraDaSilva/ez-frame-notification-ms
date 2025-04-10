package br.duosilva.tech.solutions.ez.frame.notification.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = 
	{
			"br.duosilva.tech.solutions.ez.frame.notification.ms",
			"br.duosilva.tech.solutions.ez.frame.notification.ms.adapters.in.listener",
			"br.duosilva.tech.solutions.ez.frame.notification.ms.infrastructure.config"
	})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
