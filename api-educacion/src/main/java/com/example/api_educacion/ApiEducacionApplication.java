package com.example.api_educacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiEducacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEducacionApplication.class, args);
	}

}
