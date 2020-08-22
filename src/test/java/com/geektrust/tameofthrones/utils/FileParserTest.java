package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.geektrust.tameofthrones.models.Kingdom;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileParserTest {

  private FileParser fileParser;
  private final String filePath = "inputs/";
  private final String detailsPath = "fixtures/KingdomDetails.txt";

  @BeforeEach
  void setup() throws Exception {
    fileParser = new FileParser(new HashMap<>(), new ArrayList<Kingdom>());
  }

  private String getResourcePath(String filePath) throws URISyntaxException {
    URL url = this.getClass().getClassLoader().getResource(filePath);
    Path path = Paths.get(url.toURI());
    return path.toString();
  }

  private HashMap<String, String> parseKingdomDetails() throws URISyntaxException {
    String path = getResourcePath(detailsPath);
    return fileParser.parseKingdomDetails(path);
  }

  private List<Kingdom> parseInput(String fileName) throws URISyntaxException {
    String path = getResourcePath(filePath + fileName);
    return fileParser.parseInput(path);
  }

  @Test
  void checkWhenInputFileHasValidKingdoms() throws URISyntaxException {
    String fileName = "input1.txt";
    List<Kingdom> kingdoms = parseInput(fileName);

    assertEquals(3, kingdoms.size());
  }

  @Test
  void checkWhenInputFileIsEmptyOrNotValid() throws URISyntaxException {
    String fileName = "input0.txt";
    List<Kingdom> kingdoms = parseInput(fileName);

    assertEquals("NONE", kingdoms.get(0).getKingdomName());
    
  }

  @Test
  void checkForKingdomDetailsParsing() throws URISyntaxException {
    HashMap<String, String> kingdomDetails = parseKingdomDetails();

    assertEquals(6, kingdomDetails.size());
    assertTrue(kingdomDetails.containsKey("SPACE"));
    assertEquals("Dragon", kingdomDetails.get("FIRE"));
  }
}