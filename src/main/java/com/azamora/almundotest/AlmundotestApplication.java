package com.azamora.almundotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AlmundotestApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(AlmundotestApplication.class, args);
	}

}
