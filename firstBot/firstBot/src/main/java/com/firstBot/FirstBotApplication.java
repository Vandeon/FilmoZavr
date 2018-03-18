package com.firstBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FirstBotApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FirstBotApplication.class, args);
	}
}
