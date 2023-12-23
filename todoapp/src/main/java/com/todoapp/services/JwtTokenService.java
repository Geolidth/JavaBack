package com.todoapp.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtTokenService implements TokenService {

    @Value("${token.jwt.secret}")
    private String tokenSecret;
    @Value("${token.expiration.hours}")
    private Integer expirationHours;
    @Override
    public String generateToken(UserDetails user) {
        Date expiration = Date.from(LocalDateTime.now().plusHours(expirationHours).toInstant(ZoneOffset.UTC));
        Key key= Keys.hmacShaKeyFor(tokenSecret.getBytes());
        return Jwts.builder()
                .claim("username",user.getUsername())
                .expiration(expiration)
                .signWith(key)
                //.setExpiration(expiration)
                .compact();
    }

    @Override
    public boolean isValid(String token) {
        Claims claims = getAllClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.after(new Date());
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = getAllClaims(token);
        return claims.get("username", String.class);
    }
    public Claims getAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(tokenSecret.getBytes());
//        JwtParser parser = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build();
        JwtParser parser=Jwts.parser().verifyWith(key).build();
        return parser.parseClaimsJws(token).getBody();
    }
}
