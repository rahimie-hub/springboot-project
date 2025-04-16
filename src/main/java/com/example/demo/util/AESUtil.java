package com.example.demo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * A simple utility class to encrypt and decrypt text using AES.
 *
 * This demo uses a hardcoded 32-character secret key for AES-256 encryption.
 * Note: In production environments, you should never hardcode secret keys and
 * should use more secure configurations and better encryption modes than ECB.
 */
public class AESUtil {
    // Hardcoded 32-character key for AES-256 encryption.
    // In a real application, securely manage your encryption keys.
    private static final String SECRET_KEY = "12345678901234567890123456789012";

    /**
     * Encrypts the given plain text using AES encryption.
     *
     * How it works:
     * 1. Convert the secret key (a String) into bytes using UTF-8.
     * 2. Create a SecretKeySpec object, which tells the cipher what key to use.
     * 3. Create a Cipher object for AES encryption.
     * 4. Initialize the Cipher in ENCRYPT_MODE with the key.
     * 5. Convert the plain text input to bytes (using UTF-8) and encrypt it.
     * 6. Encode the encrypted bytes into a Base64 String for easy storage/transmission.
     *
     * @param plainText the plain text input to encrypt
     * @return the encrypted text as a Base64 encoded String
     */
    public static String encrypt(String plainText) {
        try {
            // Step 1: Convert the secret key to bytes and create a key specification for AES.
            byte[] keyBytes = SECRET_KEY.getBytes("UTF-8");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            // Step 2: Create a Cipher object for AES.
            // Default transformation "AES" usually uses ECB mode with PKCS5Padding.
            // Note: ECB mode is not secure for many production purposes.
            Cipher cipher = Cipher.getInstance("AES");

            // Step 3: Initialize the cipher for encryption using our key.
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Step 4: Convert the plain text to bytes using UTF-8.
            byte[] plainTextBytes = plainText.getBytes("UTF-8");

            // Step 5: Encrypt the plain text bytes.
            byte[] encryptedBytes = cipher.doFinal(plainTextBytes);

            // Step 6: Encode the encrypted bytes to Base64 so we can get a String representation.
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception ex) {
            // Wrap any exception in a RuntimeException with a clear error message.
            throw new RuntimeException("Error encrypting text", ex);
        }
    }

    /**
     * Decrypts the given AES encrypted Base64 encoded text back into plain text.
     *
     * How it works:
     * 1. Convert the secret key (a String) into bytes using UTF-8.
     * 2. Create a SecretKeySpec object for the AES key.
     * 3. Create a Cipher object for AES decryption.
     * 4. Initialize the Cipher in DECRYPT_MODE with the key.
     * 5. Decode the Base64 encoded cipher text into bytes.
     * 6. Decrypt those bytes to retrieve the original plain text bytes.
     * 7. Convert the plain text bytes back to a String using UTF-8.
     *
     * @param cipherText the encrypted text as a Base64 encoded String
     * @return the decrypted plain text String
     */
    public static String decrypt(String cipherText) {
        try {
            // Step 1: Convert the secret key string to bytes and create a key specification.
            byte[] keyBytes = SECRET_KEY.getBytes("UTF-8");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            // Step 2: Create a Cipher object for AES decryption.
            Cipher cipher = Cipher.getInstance("AES");

            // Step 3: Initialize the cipher for decryption using our key.
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            // Step 4: Decode the Base64 encoded cipher text into a byte array.
            byte[] encryptedBytes = Base64.getDecoder().decode(cipherText);

            // Step 5: Decrypt the byte array to retrieve the original plain text bytes.
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Step 6: Convert the decrypted bytes back into a String using UTF-8.
            return new String(decryptedBytes, "UTF-8");

        } catch (Exception ex) {
            // Wrap any exception in a RuntimeException with a clear error message.
            throw new RuntimeException("Error decrypting text", ex);
        }
    }
}
