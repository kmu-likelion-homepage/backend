package kmu.likelion.homepage.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kmu.likelion.homepage.common.role.Role;

import java.util.Date;

public class JwtTokenUtil {

    public static String createToken(String email, Role role, String key, long expiresInSeconds) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("role", role.name());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiresInSeconds))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static Claims extractToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getEmail(String token, String secretKey) {
        return extractToken(token, secretKey).get("email").toString();
    }

    public static Role getRole(String token, String secretKey) {
        String roleName = extractToken(token, secretKey).get("role").toString();
        return Role.valueOf(roleName);
    }

    public static boolean isExpired(String token, String secretKey) {
        return extractToken(token, secretKey).getExpiration().before(new Date());
    }
}
