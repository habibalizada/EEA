package com.estockmarket.stock.query.api;

import com.estockmarket.stock.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EntityScan(basePackages = "com.estockmarket.stock.core.models")
@Import({ AxonConfig.class })
public class StockQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQueryApplication.class, args);
	}

}
