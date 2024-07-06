package vdg.model.logica;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class GeneradorContraseña {

	public static String generarContraseña() {
		String[] symbols = { "g", "h", "j", "z", "w", "p", "u", "o", "m", "x", "a", "b", "c", "d", "e", "f", "3", "2", "9", "8", "1", "7" };
		int length = 8;
		Random random = null;
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int indexRandom = random.nextInt(symbols.length);
			sb.append(symbols[indexRandom]);
		}
		String password = sb.toString();
		System.out.println("------------------------------------------------ "+password+" ------------------------------------");
		return password;
	}

}
