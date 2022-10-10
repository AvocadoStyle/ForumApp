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
 
public class decrypt {
  private static final String SECRET_KEY = "my_super_secret_key";
  private static final String SALT = "ssshhhhhhhhhhh!!!!";
 
  public static String decrypt(String strToDecrypt) {
    try {
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      IvParameterSpec ivspec = new IvParameterSpec(iv);
 
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
 
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
  }
  
  public static String decrypt64_username(String strToDecrypt_token){
      try{
        byte[] decodedBytes = Base64.getDecoder().decode(strToDecrypt_token);
        String decodedString = new String(decodedBytes);
        StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();
        return username;
      } catch(Exception e){
          System.out.println("Error while decrypting: " + e.toString());
      }
      return null;
  }
  public static String decrypt64_password(String strToDecrypt_token){
      try{
        byte[] decodedBytes = Base64.getDecoder().decode(strToDecrypt_token);
        String decodedString = new String(decodedBytes);
        StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();
        return password;
      } catch(Exception e){
          System.out.println("Error while decrypting: " + e.toString());
      }
      return null;
  }
public static String decrypt64(String strToDecrypt_token){
  try{
    byte[] decodedBytes = Base64.getDecoder().decode(strToDecrypt_token);
    String decodedString = new String(decodedBytes);
    return decodedString;
  } catch(Exception e){
      System.out.println("Error while decrypting: " + e.toString());
  }
  return null;
}
}

