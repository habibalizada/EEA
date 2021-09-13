package com.estockmarket.stock.cmd.api;

import com.estockmarket.stock.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class StockCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockCommandApplication.class, args);
	}

}
