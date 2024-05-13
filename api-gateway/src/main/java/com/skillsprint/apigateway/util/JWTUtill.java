package com.skillsprint.apigateway.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;


@Service
public class JWTUtill {

    @Value("${token.secret.key}")
    String jwtSecretKey;

    @Value("${token.expirationms}")
    Long jwtExpirationMs;

    //validate the token
    public void validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(String.valueOf(SECRET_KEY))
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.getSubject();

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {

        }
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUserName(String token) {
        return extractClaim (token, claims -> claims.get("sub", String.class));
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecretKey);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

}