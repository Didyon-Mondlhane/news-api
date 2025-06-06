package com.example.newsmanager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.newsmanager.domain.auth.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration}")
    private long expirationHours;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("news-api")
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getId())
                    .withClaim("role", user.getRole().toString())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

}