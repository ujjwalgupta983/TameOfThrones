package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaesarCipherTest {
  
  private CaesarCipher cipher;

  @BeforeEach
  void setup() {
    cipher = new CaesarCipher();
  }

  @Test
  void checkDecryptedMessageIsValidOrNot() {
    String cipherText = "JUPTIAC";
    int key = 5;

    assertEquals("EPKODVX", cipher.decrypt(cipherText, key));
  }

  @Test
  void checkIfCipherTextIsNull() {
    String cipherText = "";
    int key = 3;

    assertEquals("", cipher.decrypt(cipherText, key));
  }
}