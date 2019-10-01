package com.rar.utils;


import com.rar.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CheckValidity  {



    @Value("${jwt.secret}")
    private String secret;

    public String check(String token) {


        Claims claims = extract(token);
        Date expirationDate = claims.getExpiration();
        String email=claims.getSubject();
        if (expirationDate.after(new Date(System.currentTimeMillis()))) {

            return "Token Checked";

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
