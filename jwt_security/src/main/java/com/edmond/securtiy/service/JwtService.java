package com.edmond.securtiy.service;

import com.edmond.securtiy.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpiration;

    @Getter
    private Date tokenExpiration;

    @Getter
    @Setter
    private String token;

    public String generateToken(User user){
        tokenExpiration = new Date(System.currentTimeMillis() + jwtExpiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());
        claims.put("role",user.getRole());
        claims.put("iat",new Date());
        claims.put("exp",tokenExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     *
     * @param token
     * @param userDetails don't need to call database to verify the user -> X User
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        Claims claims = extractAllClaims(token);

        String userName = claims.getSubject();
        Date expiration = claims.getExpiration();

        return userName.equals(userDetails.getUsername()) && new Date().before(expiration);
    }

    public String extractUserEmail(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public String extractUserRole(String token){
        Claims claims = extractAllClaims(token);
        return (String) claims.get("role");
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
