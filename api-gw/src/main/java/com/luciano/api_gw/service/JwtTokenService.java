package com.luciano.api_gw.service;

import com.luciano.api_gw.exception.JwtTokenMalformedException;
import com.luciano.api_gw.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

    @Value("${free.endpoint}")
    private String freeEndpoint;

    private final JwtUtil jwtUtil;


    public void validateToken(ServerWebExchange exchange) throws JwtTokenMalformedException {

        String jwtToken = retrieveTokenFromRequest(exchange);

        jwtUtil.validateToken(jwtToken);

    }


    public void setHeadersWithJwtClaims (ServerWebExchange exchange) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        String jwtToken = retrieveTokenFromRequest(exchange);

        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                .header("X-Auth-User-Id", jwtUtil.getUsernameFromToken(jwtToken))
                .header("X-Auth-Roles", jwtUtil.getAuthorities(jwtToken))
                .build();

        exchange = exchange.mutate().request(serverHttpRequest).build();

    }


    public boolean isTokenNeeded(ServerHttpRequest request) {

        return !freeEndpoint.equals(request.getURI().getPath());

    }


    private String retrieveTokenFromRequest (ServerWebExchange exchange) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        List<String> authorizzations  = requestHeaders.get("authorization");

        if(authorizzations == null || StringUtils.isEmpty(authorizzations.get(0))) {
            throw new JwtTokenMalformedException("No token present");
        }

        if(!authorizzations.get(0).startsWith("Bearer ")){
            throw new JwtTokenMalformedException("Token not valid");
        }

        return authorizzations.get(0).substring(7);

    }


}
