package com.example.task3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Task3Application {

	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}

}
