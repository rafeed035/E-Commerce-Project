package com.rafeed.ecommerceproject.SecurityConfiguration.JWT;

import com.rafeed.ecommerceproject.Entity.PasswordResetToken;
import com.rafeed.ecommerceproject.Entity.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final long TOKEN_EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 1 day
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.secret}")
    private String secretKey;

    //generate Access token
    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getUserId() + "," + user.getEmail())
                .setIssuer("Admin")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    //validate the access token
    public boolean validateAccessToken(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;

        }catch (ExpiredJwtException expiredJwtException){
            LOGGER.error("JWT expired", expiredJwtException);
        } catch (IllegalArgumentException exception){
            LOGGER.error("Token is null, empty or has only whitespace", exception);
        } catch (MalformedJwtException exception){
            LOGGER.error("JWT is invalid", exception);
        } catch (UnsupportedJwtException exception){
            LOGGER.error("JWT is not supported", exception);
        } catch (SignatureException exception){
            LOGGER.error("Signature validation failed", exception);
        }
        return false;
    }

    //get the subject from the token
    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    //extract the claims
    private Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
