package com.seunghoona.kmong.member.domain;

import com.seunghoona.kmong.member.dto.TokenResponse;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
public class Token {

    private static String secretKey;
    private static long validityInMilliseconds;

    @Value("${security.jwt.token.secret-key}")
    public void setSecretKey(String b) {
        secretKey = b;
    }

    @Value("${security.jwt.token.expire-length}")
    public void setValidityInMilliseconds(long a) {
        validityInMilliseconds = a;
    }

    public static TokenResponse create(Authentication authentication) {
        final String createdToken = create(authentication.getName());
        return TokenResponse.builder()
                .token(createdToken)
                .build();
    }

    public static String create(@NotNull String payload) {
        final Claims claims = Jwts.claims().setSubject(payload);
        final Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String payload(String enterToken) {
        final String token = subStrToken(enterToken);
        verifyToken(token);
        return getPayload(token);
    }

    private String subStrToken(String token) {
        return token.substring(7);
    }

    private String getPayload(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return !claims.getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰정보입니다.");
        }
    }
}
