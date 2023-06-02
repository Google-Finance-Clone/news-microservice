package com.newsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.newsmicroservice", "com.mqconfig"})
@EnableCaching
public class NewsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsMicroserviceApplication.class, args);
	}

}
