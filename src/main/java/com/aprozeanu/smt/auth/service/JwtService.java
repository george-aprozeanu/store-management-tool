package com.aprozeanu.smt.auth.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;
    @Value("${security.jwt.expiration-time}")
    private long expirationTime;

    public JwtService(@Value("${security.jwt.secret}") String secret) {
        this.key = buildKey(secret);
        this.jwtBuilder = Jwts.builder().encryptWith(key, Jwts.ENC.A256GCM);
        this.jwtParser = Jwts.parser().decryptWith(key).build();
    }

    private static SecretKey buildKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String encrypt(String subject) {
        var now = System.currentTimeMillis();
        return jwtBuilder
            .issuedAt(new Date(now))
            .expiration(new Date(now + expirationTime))
            .subject(subject)
            .compact();
    }

    public Result decrypt(CharSequence content) {
        try {
            var claims = jwtParser.parseEncryptedClaims(content);
            var payload = claims.getPayload();
            var expiration = payload.getExpiration();
            if (expiration.after(new Date())) {
                return new Result.Success(payload.getSubject());
            } else {
                return new Result.Failure("Expired: " + expiration);
            }
        } catch (JwtException e) {
            return new Result.Failure(e.toString());
        }
    }

    public sealed interface Result permits Result.Success, Result.Failure {
        record Success(String subject) implements Result {
        }

        record Failure(String error) implements Result {
        }
    }
}
