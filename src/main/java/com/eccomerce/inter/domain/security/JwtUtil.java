package com.eccomerce.inter.domain.security;

import com.eccomerce.inter.domain.entities.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private String secretKey = "jwtcpnt";
    private int tokenValidity = 900000;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String usernameTokenGenerator(User user) {
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date(new Date().getTime() + tokenValidity)).signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public String getUserToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura Inválida", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token não suportado", e.getMessage());
        }

        return false;
    }

}
