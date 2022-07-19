package com.group10.bookingtravel.utils.jwt;

import com.group10.bookingtravel.dto.UserJwtDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTHandler {
    private final String JWT_SECRET = "s0m3s3cr3t";
    private final long JWT_EXPIRATION = 604800000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(UserJwtDTO userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        Map<String, Object> claimsData = new HashMap<>();
        claimsData.put("isAdmin", Boolean.toString(userDetails.getUser().getIsAdmin()));
        claimsData.put("username", userDetails.getUser().getUsername());
        return Jwts.builder()
                .setClaims(claimsData)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("username", String.class);
    }

    public boolean validateAdminToken(String authToken) {
        if (validateToken(authToken)) {
            System.out.println(Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken));
        }

        return true;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
