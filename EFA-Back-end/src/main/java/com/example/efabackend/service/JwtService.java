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
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
