package vdg.model.logica;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptar {

	public static String sha256(String contrasena) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
			return String.format("%064x", new BigInteger(1, hash));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}
}
