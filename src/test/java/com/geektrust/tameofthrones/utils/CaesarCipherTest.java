package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CaesarCipherTest {
    /**
     * Class that tests CaesarCipher Class
     * Decrypt the encrypted message by using CaesarCipher class
     * 
     * @param cipherText (String) is the message which we want to decrypt
     * @param key (int) is the shifting value for each char in cipherText
     * @return the decrypted message 
     * 
     */
    @Test
    public String decrypt(String cipherText, int key) {
        CaesarCipher caesar = new CaesarCipher();
        String plainText = caesar.decrypt(cipherText, key);
        assertEquals("HVIBJ", plainText);
        return plainText;
    }
    @Test
    public String emptyDecrypt(String cipherText, int key) {
        CaesarCipher caesar = new CaesarCipher();
        String plainText = caesar.decrypt(cipherText, key);
        assertEquals("", plainText);
        return plainText;
    }
}