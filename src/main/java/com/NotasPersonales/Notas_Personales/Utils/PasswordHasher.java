package com.NotasPersonales.Notas_Personales.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    private PasswordHasher(){
        throw new UnsupportedOperationException("Utility class");
    }

    public static String hash(String password){
        if (password == null || password.isBlank()) {
            return null;
        }

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        }catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }

    private static String bytesToHex(byte[] bytes){
        StringBuilder hexString = new StringBuilder(bytes.length * 2);
        for (byte b: bytes){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length()== 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
