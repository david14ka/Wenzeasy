package com.wenzeasy.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurePassword {

    public static void main(String[] args) {
        System.out.println(encrypt("123"));
        System.out.println(decrypt("123","149f04acc4da94230a0f37c15175f2f964c5cf6e"));

    }

    public static String encrypt(String passwordToHash)
    {

        byte[] salt = new byte[0];
        try {
            salt = getSalt();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return get_SHA_1_SecurePassword(passwordToHash, salt);
        //return passwordToHash;
    }

    public static Boolean decrypt(String providePassword, String securedToDecrypt){
        //return providePasword.equalsIgnoreCase(securedToDecrypt);
        return encrypt(providePassword).equalsIgnoreCase(securedToDecrypt);
    }
 
    private static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        salt = new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        return salt;
    }
}
 