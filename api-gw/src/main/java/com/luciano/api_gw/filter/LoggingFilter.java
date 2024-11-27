package com.luciano.api_gw.filter;

import com.luciano.api_gw.exception.JwtTokenMalformedException;
import com.luciano.api_gw.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingFilter implements GlobalFilter {


    private final JwtTokenService jwtTokenService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1. Pre-filter logic (optional)
        // - Modify request headers, parameters, or body
        // - Perform authentication or authorization checks
        // - Implement rate limiting or other security measures
        log.info("Received new request: {} {}.", exchange.getRequest().getMethod(), exchange.getRequest().getURI());

        if( jwtTokenService.isTokenNeeded(exchange.getRequest())) {
            jwtTokenService.validateToken(exchange);
            jwtTokenService.setHeadersWithJwtClaims(exchange);
        }

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // 2. Post-filter logic (optional)
                    // - Modify response headers or body
                    // - Log response details
                    // - Perform any additional processing
                    log.info("Response: {}", exchange.getResponse());
                }));


    }


}