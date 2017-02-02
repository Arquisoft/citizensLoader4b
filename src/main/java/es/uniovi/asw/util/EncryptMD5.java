package es.uniovi.asw.util;

import java.math.BigInteger;
import java.security.*;

public class EncryptMD5 {

	public static String encrypting(String password)
			throws NoSuchAlgorithmException {

		MessageDigest msg = MessageDigest.getInstance("MD5");
		byte[] bytes = msg.digest(password.getBytes());

		BigInteger num = new BigInteger(1, bytes);
		String newPassword = num.toString(16);

		while (newPassword.length() < 32) {
			newPassword = "0" + newPassword;
		}

		return newPassword;
	}
}
