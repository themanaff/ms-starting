package com.example.msusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUsersApplication.class, args);
	}

}
