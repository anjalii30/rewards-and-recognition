package com.rar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RewardsAndRecognitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsAndRecognitionApplication.class, args);
	}

}
