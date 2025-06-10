package fr.diginamic.spring_security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class SpringSecurityApplication {



	public static void main(String[] args) {

//		String secret = "maSuperCleSecrete123maSuperCleSecrete123";
//		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
//		String message = "Voici une chaine";
//
//		String jwt = Jwts.builder()
//				.setSubject("exemple")
//				.claim("message", message)
//				.setIssuedAt(new Date())
//				.signWith(key, SignatureAlgorithm.HS256)
//				.compact();
//
//		System.out.println("JWT : " + jwt);
//
//		// Lecture du token
//		Claims claims = Jwts.parser()
//				.setSigningKey(key)
//				.build()
//				.parseClaimsJws(jwt)
//				.getBody();
//
//		System.out.println("message extrait du JWT : "+ claims.get("message"));


//		String secret = "maSuperCleSecrete123maSuperCleSecrete123";
//		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
//
//		List<String> tokens = List.of(
//				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTMxNX0.VIBNB1C1j93PUDrbmFJwbJXXbTYNPwEGJbkEQHVZoYg",
//				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTYwNn0.UZ6IO0Wvrnd4NP63diYjyvkNFNWI1NfDGP9lpfJyJSE",
//				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHRpZ25lciIsImlhdCI6MTc0NDgzMTY0NX0.5vpcu1T7DmXDuoCBLhBJAQGE3HpNUO41-Tr0rkGrDY0",
//				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTY3OH0.NXvOGwyMKQ9tK2z5dR6ER5tbf2plLlkxgJnCQ0lI13g"
//		);
//
//		for (String token : tokens) {
//			try {
//				Claims claims = Jwts.parser()
//						.setSigningKey(key)
//						.build()
//						.parseClaimsJws(token)
//						.getBody();
//
//				System.out.println("Token is valid. Message: " + claims.get("message"));
//			} catch (JwtException e) {
//				System.out.println("Token is invalid: " + e.getMessage());
//			}
//
//		}

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZSBuZSBzYWlzIHBhcyIsIm1lc3NhZ2UiOiLDoCBtb2kgbcOqbWUiLCJtZXNzYWdlLWNhY2jDqSI6InRyYXZhaWxsZSwgw6dhIGZpbml0IHRvdXJqb3VycyBwYXIgcGF5ZXIiLCJsaHVtb3VyIjoiYydlc3QgaW1wb3J0YW50IiwiaWF0IjoxNzQ0ODMxOTM1fQ.wdaFguIzdkNKgVaYmSg5jHgYCDenufwjlJEL7T42fLA";

		String[] secretKeys = {
				"essayeDoncCetteChouetteCleSecreteOnVerraSiCaMarche",
				"maToutAutantChouetteCleSecreteQueJeChoisiCommeJeVeux"
		};

		for (String secret : secretKeys) {
			try {
				SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
				Claims claims = Jwts.parser()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(token)
						.getBody();

				System.out.println("Subject: " + claims.getSubject());
				System.out.println("Message: " + claims.get("message"));
				System.out.println("La clé secrète '" + secret + "' est valide pour ce JWT.");

			} catch (JwtException e) {
				System.out.println("La clé secrète '" + secret + "' n'est pas valide pour ce JWT.");
			}
		}




	}
}
