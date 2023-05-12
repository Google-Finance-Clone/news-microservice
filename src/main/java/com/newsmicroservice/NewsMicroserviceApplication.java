package com.newsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.newsmicroservice", "com.mqconfig"})
public class NewsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsMicroserviceApplication.class, args);
	}

}
