package com.br.supercevaja.Super.CevaJa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtUtils {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String ROLE_CLAIM = "ROLE_USER";


    public static String generateToken(String username) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGenerator.generateKey();

        byte[] keyBytes = secretKey.getEncoded();


        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);

        SecretKey key = new SecretKeySpec(encodedKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(username)
                .claim(ROLE_CLAIM, "ROLE_USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        byte[] keyBytes = SECRET_KEY.getEncoded();
        SecretKey key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public UsernamePasswordAuthenticationToken validateToken(String token) {

        token = token.replace("Bearer ", "");
        byte[] keyBytes = SECRET_KEY.getEncoded();
        SecretKey key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            String user = body.get(Claims.ID, String.class);
            if (user != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, null);
                return usernamePasswordAuthenticationToken;
            }
        }
        return null;
    }

}