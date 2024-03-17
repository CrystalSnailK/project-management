package com.example.demo.Security.Unitls;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtils {

    @Value("${jwt.expiration}")
    private static long EXPIRES_TIME = 1000 * 60 * 60 * 24;

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken() throws UnsupportedEncodingException {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRES_TIME))
                .signWith(KEY)
                .compact();
    }
    public static boolean verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
//    public static void refreshToken(String token) {
//
//    }
}
