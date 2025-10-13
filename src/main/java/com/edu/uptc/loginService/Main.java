package com.edu.uptc.loginService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:secrets/.env.properties", ignoreResourceNotFound = true)
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
