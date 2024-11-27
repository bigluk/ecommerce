package com.luciano.api_gw.util;

import com.luciano.api_gw.exception.JwtTokenMalformedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.signature.secretKey}")
    private String SECRET_KEY;


    public Claims getClaims(final String token) {

        return Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token).getPayload();

    }



    public String getAuthorities(String token) {

        List<String> retVal = new ArrayList<>();

        Claims claims  = this.getAllClaimsFromToken(token);
        String[] authorities = String.valueOf(claims.get("authorities")).replace("[", "").replace("]", "").split(",");

        for (String authority : authorities) {
            retVal.add(authority.trim());
        }

        return retVal.get(0);
    }



    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }



    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }



    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }



    public void validateToken(final String token) throws JwtTokenMalformedException {

        try {
            Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token).getPayload();
        }
        catch (SignatureException ex) {
            throw new JwtTokenMalformedException("JWT signature not valid");
        }
        catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Token JWT not valid");
        }
        catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Token JWT expired");
        }
        catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex) {
            throw new JwtTokenMalformedException("JWT claims string is empty.");
        }
    }


    private SecretKey getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
