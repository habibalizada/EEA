package com.estockmarket.userestockauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserEStockAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserEStockAuthApplication.class, args);
	}

}
