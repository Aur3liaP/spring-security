package fr.diginamic.spring_security;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;

@SpringBootApplication
public class SpringSecurityApplication {

	public static String getHash(String input) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		String hex = HexFormat.of().formatHex(hash);
		return (hex);
	}

	private static final List<String> PRENOMS = Arrays.asList(
			"Aurélia", "Elisa", "Geoffroy", "Chloé", "Carole", "Paul", "Pierre", "Lucas"
	);


	public static void main(String[] args) {

		System.out.println("=== PARTIE 1 : Hash SHA-256 des prénoms ===\n");
		for (String prenom : PRENOMS) {
			try {
				String hash = getHash(prenom);
				System.out.printf("Prénom: %-12s | Hash: %s%n", prenom, hash);
			} catch (NoSuchAlgorithmException e) {
				System.err.println("Erreur lors du calcul du hash pour " + prenom + ": " + e.getMessage());
			}
		}

		System.out.println("\n=== PARTIE 2 : Mining avec nonce pour hash commençant par '0000' ===\n");
		partie2();

	}

	private static void partie2() {
		for (String prenom : PRENOMS) {
			System.out.printf("Mining pour '%s'...", prenom);

			long startTime = System.currentTimeMillis();
			int nonce = findNonceForTarget(prenom, "0000");
			long endTime = System.currentTimeMillis();

			try {
				String neoPrenom = prenom + nonce;
				String finalHash = getHash(neoPrenom);

				System.out.printf(" Trouvé !%n");
				System.out.printf("  Prénom: %s%n", prenom);
				System.out.printf("  Nonce: %d%n", nonce);
				System.out.printf("  neoPrenom: %s%n", neoPrenom);
				System.out.printf("  Hash final: %s%n", finalHash);
				System.out.printf("  Temps: %d ms%n", (endTime - startTime));
				System.out.println();

			} catch (NoSuchAlgorithmException e) {
				System.err.println("Erreur lors du calcul du hash final: " + e.getMessage());
			}
		}
	}

	private static int findNonceForTarget(String input, String target) {
		int nonce = 1;

		while (true) {
			try {
				String candidate = input + nonce;
				String hash = getHash(candidate);

				if (hash.startsWith(target)) {
					return nonce;
				}

				nonce++;

				if (nonce % 10000 == 0) {
					System.out.printf(" [%d essais]", nonce);
				}

			} catch (NoSuchAlgorithmException e) {
				System.err.println("Erreur lors du mining: " + e.getMessage());
				return -1;
			}
		}
	}

}
