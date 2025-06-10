package fr.diginamic.spring_security;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;

@SpringBootApplication
public class SpringSecurityApplication {




	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		hashDifferentesChaines(encoder);

		demonstrationToto(encoder);

		hashMemesChaines(encoder);

		explicationBCrypt(encoder);

	}

	private static void hashDifferentesChaines(BCryptPasswordEncoder encoder) {
		System.out.println("1. Hash de différentes chaînes de caractères :");

		String[] testStrings = {"hello", "toto"};

		for (String str : testStrings) {
			String hash = encoder.encode(str);
			System.out.printf("Chaîne: %-10s | Hash: %s%n", "\"" + str + "\"", hash);
		}
		System.out.println();
	}

	private static void demonstrationToto(BCryptPasswordEncoder encoder) {
		System.out.println("2. Focus sur \"toto\" - Test de reproductibilité :");

		// Premier hash de "toto"
		String hash1 = encoder.encode("toto");
		System.out.println("Premier hash de \"toto\" :");
		System.out.println(hash1);

		// Deuxième hash de "toto"
		String hash2 = encoder.encode("toto");
		System.out.println("\nDeuxième hash de \"toto\" (même session) :");
		System.out.println(hash2);

	}

	private static void hashMemesChaines(BCryptPasswordEncoder encoder) {
		System.out.println("\n3. BCrypt avec matches() :");

		// Génération de deux hash différents pour "toto"
		String totohaseh = encoder.encode("toto");
		String newotohaseh = encoder.encode("toto");

		System.out.println("totohaseh  = " + totohaseh);
		System.out.println("newotohaseh= " + newotohaseh);
		System.out.println();

		// Test avec matches()
		System.out.println("Test avec encoder.matches() :");
		boolean result1 = encoder.matches("toto", totohaseh);
		boolean result2 = encoder.matches("toto", newotohaseh);

		System.out.println("encoder.matches(\"toto\", totohaseh):   " + result1);
		System.out.println("encoder.matches(\"toto\", newotohaseh): " + result2);

	}

	// Demande d'explication à Claude :

	private static void explicationBCrypt(BCryptPasswordEncoder encoder) {
		System.out.println("\n4. Explication du fonctionnement de BCrypt de Claude :");
		System.out.println("=============================================");

		String hash = encoder.encode("example");
		System.out.println("Exemple de hash BCrypt : " + hash);
		System.out.println();

		// Analyse de la structure du hash
		if (hash.startsWith("$2a$") || hash.startsWith("$2b$") || hash.startsWith("$2y$")) {
			String[] parts = hash.split("\\$");
			if (parts.length >= 4) {
				System.out.println("Structure du hash BCrypt :");
				System.out.println("├─ Version:    $" + parts[1] + "$");
				System.out.println("├─ Coût:      $" + parts[2] + "$");
				System.out.println("├─ Salt:      " + parts[3].substring(0, 22) + " (22 caractères)");
				System.out.println("└─ Hash:      " + parts[3].substring(22) + " (31 caractères)");
			}
		}

		System.out.println("\n🔍 Points clés de BCrypt :");
		System.out.println("• Chaque hash contient son propre SALT unique aléatoire");
		System.out.println("• Le salt est stocké DANS le hash lui-même");
		System.out.println("• matches() extrait le salt du hash pour re-calculer");
		System.out.println("• C'est pourquoi deux hash différents peuvent matcher le même mot de passe");
		System.out.println("• BCrypt est résistant aux attaques rainbow table grâce au salt");

		// Démonstration supplémentaire
		System.out.println("\n5. Démonstration supplémentaire :");
		System.out.println("==================================");

		// Test avec un mauvais mot de passe
		String correctHash = encoder.encode("secret");
		System.out.println("Hash pour 'secret': " + correctHash);
		System.out.println("matches('secret', hash):  " + encoder.matches("secret", correctHash));
		System.out.println("matches('wrong', hash):   " + encoder.matches("wrong", correctHash));
		System.out.println("matches('SECRET', hash):  " + encoder.matches("SECRET", correctHash));

		System.out.println("\n✨ BCrypt = Sécurité + Simplicité d'utilisation !");
	}


}
