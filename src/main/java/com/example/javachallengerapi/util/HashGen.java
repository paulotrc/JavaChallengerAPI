package com.example.javachallengerapi.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGen {

    private static final String ALGORITHM = "SHA-256";

    /**
     * Gerador de hash para criptografia de senha do usuário
     * @param original senha definida pelo usuário
     * @return String senha após aplicação de hash
     */
    public static String generateHash(String original) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        byte[] encodedhash = digest.digest(
                original.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();

    }

}
