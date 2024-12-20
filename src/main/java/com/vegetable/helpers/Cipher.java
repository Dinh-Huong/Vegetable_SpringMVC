package com.vegetable.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cipher {
	public static String GenerateMD5(String data) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(data.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashText = no.toString(16);
			while(hashText.length() < 32) {
				hashText += "0" + hashText;
			}
			return hashText;
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
