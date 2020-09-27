package org.rldevelopement.back.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

public class Password {

   public static String getHash(String text) {
       String textToHash = text;
       String generatedPassword = null;
       try {
           // Create MessageDigest instance for MD5
           MessageDigest md = MessageDigest.getInstance("MD5");
           //Add password bytes to digest
           md.update(text.getBytes());
           //Get the hash's bytes
           byte[] bytes = md.digest();
           //This bytes[] has bytes in decimal format;
           //Convert it to hexadecimal format
           StringBuilder sb = new StringBuilder();
           for(int i=0; i< bytes.length ;i++)
           {
               sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
           }
           //Get complete hashed password in hex format
           generatedPassword = sb.toString();
       }
       catch (NoSuchAlgorithmException e)
       {
           e.printStackTrace();
       }
       System.out.println(generatedPassword);

       return generatedPassword;
   }

}
