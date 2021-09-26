package com.example.javachallengerapi.util;

import com.example.javachallengerapi.exception.JWTValidationException;
import com.example.javachallengerapi.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JWTUtil {

    private static final SecretKey CHAVE = Keys.hmacShaKeyFor("284c3f2534fe741fbbaf5f84ba1e90c46b56d7bfe87681679749cb85be11dd38"
            .getBytes(StandardCharsets.UTF_8));

    public static String gerarJWTUsuario(Usuario usuario) throws JWTValidationException {
        try {
            String jwtToken = Jwts.builder()
                    .setSubject(usuario.getLogin())
                    .setIssuer("127.0.0.1:8080")
                    .setIssuedAt(new Date())
                    .setExpiration(
                            Date.from(
                                    LocalDateTime.now().plusMinutes(5L)
                                            .atZone(ZoneId.systemDefault())
                                            .toInstant()))
                    .signWith(CHAVE, SignatureAlgorithm.RS512)
                    .compact();

            return jwtToken;
        }catch (JwtException e){
            throw new JWTValidationException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getLocalizedMessage());
        }
    }

    public static boolean validarTokenJWT(String token, Usuario usuario) throws JWTValidationException {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(CHAVE)
                    .build()
                    .parseClaimsJws(token);

            String loginValidar = claimsJws.getBody().getSubject().split(":")[0];

            return true;

        }catch (JwtException e){
            throw new JWTValidationException(HttpStatus.UNAUTHORIZED.toString(), e.getLocalizedMessage());
        }
    }
}
