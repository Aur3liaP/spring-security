package fr.diginamic.spring_security.service;

import fr.diginamic.spring_security.entity.UserApp;
import fr.diginamic.spring_security.repository.UserAppRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtAuthentificationService {
    private final String secret =  "monSecretLeMieuxGardeDeTouteMaVieEntiereQuePersonneNeDoitSavoir";

    @Autowired
    private UserAppRepository userAppRepository;

    public String generateToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean authenticate(String username, String password) {
        UserApp user = userAppRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }
}
