package models;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.*;

public class Security {
	public static String encrypt(String strToEncrypt, String originalKey, String cipherType) {
		String encryptedString = null;
                byte[] byteKey = originalKey.getBytes();
		try {
			Cipher cipher = Cipher.getInstance(cipherType);
			final SecretKeySpec secretKey = new SecretKeySpec(byteKey, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return encryptedString;
	}

	public static String decrypt(String codeDecrypt, String originalKey, String cipherType){
		String decryptedString = null;
                byte[] byteKey = originalKey.getBytes();
		try{
			Cipher cipher = Cipher.getInstance(cipherType);
			final SecretKeySpec secretKey = new SecretKeySpec(byteKey, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			decryptedString = new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return decryptedString;
	}	
}
