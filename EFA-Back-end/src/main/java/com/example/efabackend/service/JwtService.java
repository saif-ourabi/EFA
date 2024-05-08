package com.example.efabackend.service;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "275GdLekMZQQ3xww7qmVUxhktTQ3wlTf";
    private static final long EXPIRATION_TIME = 36000;

    public static String generateToken(String email) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static boolean isValidToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public  static String decodeToken(String jwtToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid JWT token");
        }
    }
}
