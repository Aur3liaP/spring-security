package fr.diginamic.spring_security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthentificationService {
    private final String secret =  "monSecretLeMieuxGardeDeTouteMaVieEntiereQuePersonneNeDoitSavoir";

    public Boolean validateJwt(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String generateToken() {
        return Jwts.builder()
                .setSubject("subject")
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
