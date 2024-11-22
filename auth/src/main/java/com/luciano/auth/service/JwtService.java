package com.luciano.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;



@Service
public class JwtService {


    @Value("${jwt.signature.secretKey}")
    private String SECRET_KEY;

    

    public String createToken(User user) {
        
        Map<String, ?> claims = new HashMap<>();
                
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .claim("ROLE", user.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey())
                .compact();

    }



    private SecretKey getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}