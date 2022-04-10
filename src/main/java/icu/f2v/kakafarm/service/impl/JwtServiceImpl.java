package icu.f2v.kakafarm.service.impl;

import antlr.Token;
import icu.f2v.kakafarm.TokenException;
import icu.f2v.kakafarm.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;

public class JwtServiceImpl implements JwtService {
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String getNewToken(String userId) {
        return Jwts.builder().setSubject(userId).signWith(key).compact();
    }

    @Override
    public String verifyToken(String token) throws TokenException {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenException();
        }
    }


}
