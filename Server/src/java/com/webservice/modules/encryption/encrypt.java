package com.webservice.modules.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.StringTokenizer;
 
public class encrypt {
  private static final String SECRET_KEY = "my_super_secret_key";
  private static final String SALT = "ssshhhhhhhhhhh!!!!";
 
public static String encrypt(String strToEncrypt) {
    try {
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      IvParameterSpec ivspec = new IvParameterSpec(iv);
 
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
 
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
      return Base64.getEncoder()
          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
}
  
public static String encrypt64(String strToEncrypt_token){
    try{
        String encodeString = Base64.getEncoder().encodeToString(strToEncrypt_token.getBytes());
//        StringTokenizer tokenizer = new StringTokenizer(encodeString, ":");
//        String username = tokenizer.nextToken();
//        String password = tokenizer.nextToken();
//        String[] user_password = new String[2];
//        user_password[0] = username;
//        user_password[1] = password;
//        return user_password;
    } catch(Exception e){
        System.out.println("Error while decrypting: " + e.toString());
    }
        return null;
}


}