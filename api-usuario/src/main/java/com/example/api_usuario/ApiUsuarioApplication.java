package com.example.api_usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiUsuarioApplication.class, args);
	}

}
