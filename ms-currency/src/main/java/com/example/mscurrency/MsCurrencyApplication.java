package com.example.mscurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCurrencyApplication.class, args);
	}

}
