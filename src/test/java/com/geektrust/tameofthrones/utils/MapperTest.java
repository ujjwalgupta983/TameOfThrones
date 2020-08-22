package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.geektrust.tameofthrones.dto.KingdomDto;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.models.Ruler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapperTest {
  
  private FileParser fileParser;
  private Mapper mapper;
  
  private final String filePath = "src\\test\\resources\\inputs\\";
  private final String detailsPath = "src\\test\\resources\\fixtures\\KingdomDetails.txt";

  @BeforeEach
  void setup() throws Exception {
    fileParser = new FileParser(new HashMap<>(), new ArrayList<Kingdom>());
    mapper = new Mapper(new ArrayList<>(), new Ruler());
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
  void validateMappingForMultipleKingdomOfSameName() {

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
  void validateRulerIfRulerKingdomIsChanged() {
    Ruler ruler = mapRuler(parseKingdomDetails(), "FIRE");
    if (ruler.getSubjects() != null) {
      assertEquals("Dragon", ruler.getEmblem());
    }
  }

  private HashMap<String, String> parseKingdomDetails() {
    return fileParser.parseKingdomDetails(detailsPath);
  }

  private List<Kingdom> parseInput(String fileName) {
    return fileParser.parseInput(filePath + fileName);
  }


}