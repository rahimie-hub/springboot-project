package com.example.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

/**
 * A simple utility class for hashing passwords using the SHA-256 algorithm.
 *
 * This class contains one static method, hashPassword, that converts a plain text
 * password into a SHA-256 hashed string represented in hexadecimal format.
 */
public class SecurityUtil {

    /**
     * Hashes the given password using SHA-256.
     *
     * Steps:
     * 1. Get a MessageDigest instance for SHA-256.
     * 2. Convert the input password to a byte array using UTF-8 encoding.
     * 3. Compute the SHA-256 digest of the password.
     * 4. Convert the resulting byte array to a hexadecimal string.
     * 5. Return the hexadecimal string.
     *
     * @param password the plain text password to be hashed.
     * @return a hexadecimal string representation of the SHA-256 hash.
     */
    public static String hashPassword(String password) {
        try {
            // Step 1: Create a MessageDigest instance for SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Step 2: Convert the password into a byte array using UTF-8 encoding
            byte[] passwordBytes = password.getBytes("UTF-8");

            // Step 3: Compute the SHA-256 hash (digest) of the password bytes
            byte[] hashBytes = messageDigest.digest(passwordBytes);

            // Step 4: Convert the byte array (hash) into a hexadecimal string.
            // We build this string by converting each byte to a 2-digit hexadecimal.
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                // Convert the byte to an unsigned integer and then to a hex string.
                String hex = Integer.toHexString(b & 0xff);
                
                // If the hex string is a single character, prepend "0" to make it two characters.
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
            
            // Step 5: Return the complete hexadecimal string.
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            // This exception should not occur because "SHA-256" is a standard algorithm.
            throw new RuntimeException("SHA-256 algorithm not found.", e);
        } catch (UnsupportedEncodingException e) {
            // This exception should not occur because "UTF-8" is standard.
            throw new RuntimeException("UTF-8 encoding not supported.", e);
        }
    }
}
