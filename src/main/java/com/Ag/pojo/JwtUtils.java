package com.Ag.pojo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 建议放到配置里
    private static final Key SIGN_KEY =
            Keys.hmacShaKeyFor("Agbczl-Agbczl-Agbczl-Agbczl-Agbczl".getBytes());

    // 过期时间（1小时）
    private static final long EXPIRE = 1000 * 60 * 60;

    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SIGN_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

     public static Claims parseJWT(String jwt) {
     return Jwts.parserBuilder()
     .setSigningKey(SIGN_KEY)
     .build()
     .parseClaimsJws(jwt)
     .getBody();
     }
     }
