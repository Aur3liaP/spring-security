package fr.diginamic.spring_security;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
public class SpringSecurityApplication {

	private static final List<String> PRENOMS = Arrays.asList(
			"Aurélia", "Elisa", "Geoffroy", "Chloé", "Carole", "Paul", "Pierre", "Lucas"
	);

	public static void hashHmac() throws NoSuchAlgorithmException, InvalidKeyException {
		String secretKey = "cleSecrete";
		String message = "Voici unechaine à signer";

		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");

		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(secretKeySpec);

		byte[] hmacBytes = mac.doFinal(message.getBytes());

		String hmacBase64 = Base64.getEncoder().encodeToString(hmacBytes);
		System.out.println("Signature : " + hmacBase64);
	}

	// Méthode pour calculer le HMAC d'un prénom
	public static String calculateHmacForName(String prenom, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");

		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(secretKeySpec);

		byte[] hmacBytes = mac.doFinal(prenom.getBytes());

		return Base64.getEncoder().encodeToString(hmacBytes);
	}

	// Méthode pour calculer les HMAC de tous les prénoms
	public static void calculateAllHmacs() {
		String mySecretKey = "Didier2025";

		System.out.println("=== Calcul HMAC-SHA256 des prénoms ===");

		for (String prenom : PRENOMS) {
			try {
				String hmac = calculateHmacForName(prenom, mySecretKey);
				System.out.println("Prénom: " + String.format("%-8s", prenom) + " -> HMAC: " + hmac);
			} catch (NoSuchAlgorithmException | InvalidKeyException e) {
				System.err.println("Erreur lors du calcul HMAC pour " + prenom + ": " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("=== Exemple original ===");
			hashHmac();
			System.out.println();

			calculateAllHmacs();

		} catch (Exception e) {
			System.err.println("Erreur: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
