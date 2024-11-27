package com.luciano.api_gw.config;

import com.luciano.api_gw.filter.LoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig
{
    //@Autowired
    //private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder)
    {
        return builder
                .routes()
                .route("itemMicroservice", r -> r
                        .path("/api/v1/item/find/{barcode}")
                        .and().method("GET")
                        //.filters(f -> f.filter(jwtAuthFilter))
                        .uri("http://localhost:5051"))
                .route("authMicroservice", r -> r
                        .path("/api/v1/token/create")
                        .and().method("POST")
                        .uri("http://localhost:5052"))
                .build();

    }
}

