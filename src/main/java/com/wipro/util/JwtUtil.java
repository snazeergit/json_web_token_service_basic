package com.wipro.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret should be at least 256 bits (32 chars minimum for HS256)
    private final String SECRET = "mySecretKeymySecretKeymySecretKey12";

    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    //  Generate secure key
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    //  Generate Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)   //  use this for compatibility
                .setIssuer("nazeer-app")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }

    //  Extract Username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    //  Validate Token
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isExpired(token);
    }

    //  Check Expiry
    private boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    //  Parse claims (NEW parser API)
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}