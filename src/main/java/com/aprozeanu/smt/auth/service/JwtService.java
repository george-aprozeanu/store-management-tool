package com.aprozeanu.smt.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key key;

    @Value("${security.jwt.expiration-time}")
    private long expirationTime;

    public JwtService(@Value("${security.jwt.secret}") String secret) {
        this.key = buildKey(secret);
    }

    private static Key buildKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getToken(String content) {
        var now = System.currentTimeMillis();
        var expiration = now + expirationTime;
        return Jwts.builder().content(content)
            .issuedAt(new Date(now))
            .expiration(new Date(expiration))
            .signWith(key).compact();
    }
}
