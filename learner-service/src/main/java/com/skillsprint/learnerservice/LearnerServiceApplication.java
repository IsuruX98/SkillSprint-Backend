package com.skillsprint.learnerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LearnerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnerServiceApplication.class, args);
	}

}
