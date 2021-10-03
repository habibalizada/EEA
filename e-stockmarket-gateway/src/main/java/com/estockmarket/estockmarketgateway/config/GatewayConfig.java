package com.estockmarket.estockmarketgateway.config;

import com.estockmarket.estockmarketgateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
				.route("COMPANY-SERVICE", r -> r.path("/api/v1.0/market/company/**").filters(f -> f.filter(filter)).uri("lb://COMPANY-SERVICE"))
				.route("STOCK-COMMAND", r -> r.path("/api/v1.0/market/stock/command/**").filters(f -> f.filter(filter)).uri("lb://STOCK-COMMAND"))
				.route("STOCK-QUERY", r -> r.path("/api/v1.0/market/stock/query/**").filters(f -> f.filter(filter)).uri("lb://STOCK-QUERY"))
				.build();
	}

}
