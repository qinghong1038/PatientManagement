package com.example.ahmad.patientmanagement;
import android.util.Base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Ahmad on 2016/10/22.
 */

public class AES {
    private static String algorithm = "AES";
    private static byte[] keyValue;//=new byte[] {'a','b','c','d','e','f','g','h','a','b','c','d','e','f','g','h'};// your key

   public static void setKey(String key){
       keyValue = key.getBytes();
   }

    public static String encrypt(String plainText) throws Exception
    {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = chiper.doFinal(plainText.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);//new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception
    {
        // generate key
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.decode(encryptedText, Base64.DEFAULT);//.decodeBuffer(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    // Performs decryption
    public static void decryptFile(String fileURL, String newFileURL) throws Exception
    {
        // generate key
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);
        FileInputStream fis = new FileInputStream(fileURL);
        FileOutputStream fos = new FileOutputStream(newFileURL);
        CipherInputStream input = new CipherInputStream(fis, chiper);

        final byte[] decryptedData = new byte[4096];
        int decryptedRead;
        while ((decryptedRead = input.read(decryptedData)) >= 0) {
            fos.write(decryptedData, 0, decryptedRead);
        }
        fos.flush();
        fos.close();
        input.close();
        fis.close();

    }

    //generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(keyValue, algorithm);
        return key;
    }
}