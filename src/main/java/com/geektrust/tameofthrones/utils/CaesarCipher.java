package com.geektrust.tameofthrones.utils;

public class CaesarCipher {

  /**
   * CaesarDecrypt class decrypt the message and parse it into kingdomDTO object.
   * 
   * @param cipherText (String) is the message which we want to decrypt
   * @param key (int) is the shifting value for each char in cipherText
   * @return String decryptMsg returns the decrypted message 
   */

  private String decryptMsg;

  public String decrypt(String cipherText, int key) {

    decryptMsg = new String();
    for (int i = 0; i < cipherText.length(); i++) {
      // Shift 1 character at a time
      char character = cipherText.charAt(i);
      // if character lies b/w a and z 
      if (character >= 'a' && character <= 'z') {
        // shift character
        character = (char) (character - key);

        // shift character lesser than 'a'
        if (character < 'a') {
          // re-shift to starting position 
          character = (char) (character - 'a' + 'z' + 1);
        }
        decryptMsg = decryptMsg + character;
      } else if (character >= 'A' && character <= 'Z') {
        // shift character
        character = (char) (character - key);
            
        //shift character lesser than 'A'
        if (character < 'A') {
          // re-shift to starting position 
          character = (char) (character - 'A' + 'Z' + 1);
        }
        decryptMsg = decryptMsg + character;            
      } else {
        decryptMsg = decryptMsg + character;            
      } 
    }

    return decryptMsg;
  }

}