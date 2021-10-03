package com.estockmarket.estockmarketgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EStockmarketGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStockmarketGatewayApplication.class, args);
	}

}
