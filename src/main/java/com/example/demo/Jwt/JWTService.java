package com.example.demo.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails user_) {

        return getToken(new HashMap<>(), user_);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user_) {

        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user_.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey() {
        
        byte[] keybites = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybites);
    }

    public String getusernameFromToken(String token) {

        return getClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails user) {

        final String username = getusernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){

        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){

        return getClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){

        return getExpiration(token).before(new Date());
    }

}
