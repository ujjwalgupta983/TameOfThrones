package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.geektrust.tameofthrones.dto.KingdomDto;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.models.Ruler;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapperTest {
  
  private FileParser fileParser;
  private Mapper mapper;
  
  private final String filePath = "inputs/";
  private final String detailsPath = "fixtures/KingdomDetails.txt";

  @BeforeEach
  void setup() throws Exception {
    fileParser = new FileParser(new HashMap<>(), new ArrayList<Kingdom>());
    mapper = new Mapper(new ArrayList<>(), new Ruler());
  }

  private String getResourcePath(String filePath) throws URISyntaxException {
    URL url = this.getClass().getClassLoader().getResource(filePath);
    Path path = Paths.get(url.toURI());
    return path.toString();
  }

  private List<KingdomDto> mapSubjects(HashMap<String, String> kingdomDetails,
      List<Kingdom> kingdoms) {
    return mapper.mapSubjects(kingdomDetails, kingdoms);
  }

  private Ruler mapRuler(HashMap<String, String> kingdomDetails,
      String rulerName) {
    return mapper.mapRuler(kingdomDetails, rulerName);
  }

  @Test
  void validateMappingForMultipleKingdomOfSameName() throws URISyntaxException {

    String fileName = "input4.txt";

    List<KingdomDto> subjects = mapSubjects(parseKingdomDetails(),
        parseInput(fileName));
    if (subjects != null) {
      assertEquals(5, subjects.size());
      assertEquals("AIR", subjects.get(0).getKingdomName());
      assertEquals("HASDKDAJSDAS", subjects.get(0).getMessage());
      assertEquals("Dragon", subjects.get(1).getEmblem());
    }
  }

  @Test
  void validateRulerIfRulerKingdomIsChanged() throws URISyntaxException {
    Ruler ruler = mapRuler(parseKingdomDetails(), "FIRE");
    if (ruler.getSubjects() != null) {
      assertEquals("Dragon", ruler.getEmblem());
    }
  }

  private HashMap<String, String> parseKingdomDetails() throws URISyntaxException {
    String path = getResourcePath(detailsPath);
    return fileParser.parseKingdomDetails(path);
  }

  private List<Kingdom> parseInput(String fileName) throws URISyntaxException {
    String path = getResourcePath(filePath + fileName);
    return fileParser.parseInput(path);
  }


}