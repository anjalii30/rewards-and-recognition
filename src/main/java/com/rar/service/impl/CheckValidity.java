package com.rar.service.impl;

import com.rar.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.rar.utils.Constants.secret;

@Service
public class CheckValidity  {

    public String check(String token) {

        Claims claims = extract(token);
        Date expirationDate = claims.getExpiration();
        String email=claims.getSubject();
        if (expirationDate.after(new Date(System.currentTimeMillis()))) {

            return email;

        } else {
            throw new InvalidTokenException("token is expired");
        }
    }

    private Claims extract(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
