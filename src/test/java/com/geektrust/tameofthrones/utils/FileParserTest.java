package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.geektrust.tameofthrones.models.Kingdom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileParserTest {
  
  private FileParser fileParser;
  private final String filePath = "src\\test\\resources\\inputs\\";
  private final String detailsPath = "src\\test\\resources\\fixtures\\KingdomDetails.txt";

  @BeforeEach
  void setup() throws Exception {
    fileParser = new FileParser(new HashMap<>(), new ArrayList<Kingdom>());
  }

  private HashMap<String, String> parseKingdomDetails() {

    return fileParser.parseKingdomDetails(detailsPath);
  }

  private List<Kingdom> parseInput(String fileName) {

    return fileParser.parseInput(filePath + fileName);
  }

  @Test
  void checkWhenInputFileHasValidKingdoms() {
    String fileName = "input1.txt";
    List<Kingdom> kingdoms = parseInput(fileName);

    assertEquals(3, kingdoms.size());
  }

  @Test
  void checkWhenInputFileIsEmptyOrNotValid() {
    String fileName = "input0.txt";
    List<Kingdom> kingdoms = parseInput(fileName);

    assertEquals("NONE", kingdoms.get(0).getKingdomName());
    
  }

  @Test
  void checkForKingdomDetailsParsing() {
    HashMap<String, String> kingdomDetails = parseKingdomDetails();

    assertEquals(6, kingdomDetails.size());
    assertTrue(kingdomDetails.containsKey("SPACE"));
    assertEquals("Dragon", kingdomDetails.get("FIRE"));
  }
}