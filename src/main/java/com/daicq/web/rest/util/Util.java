package com.daicq.web.rest.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Util {
    static final String SECRET_KEY = "stackjava.com.if";
    static SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

    public static void createKey(){

    }
    public static String encryptAES(String s_text) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
      //  String original = "stackjava.com";
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] byteEncrypted = cipher.doFinal(s_text.getBytes());
        String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
        return encrypted;
    }

    public static String decryptAES(String s_encrypted) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] byteEncryted = s_encrypted.getBytes();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(byteEncryted);
        String decrypted = new String(byteDecrypted);
        return decrypted;
    }

    public static String ecryptMD5(String clearText){
        return null;
    }
}
