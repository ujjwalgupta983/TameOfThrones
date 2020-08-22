package com.geektrust.tameofthrones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.geektrust.tameofthrones.dto.KingdomDto;
import com.geektrust.tameofthrones.models.Ruler;
import com.geektrust.tameofthrones.services.RulerService;
import com.geektrust.tameofthrones.utils.FileParser;
import com.geektrust.tameofthrones.utils.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TameOfThronesTest {
  
  private TameOfThrones tameOfThrones;
  private Ruler ruler;

  private final String filePath = "src\\test\\resources\\inputs\\";
  private final String detailsPath = "src\\test\\resources\\fixtures\\KingdomDetails.txt";

  @BeforeEach
  void setup() throws Exception {
    FileParser fileParser = new FileParser(new HashMap<>(), new ArrayList<>());
    Mapper mapper = new Mapper(new ArrayList<>(), new Ruler());
    tameOfThrones = new TameOfThrones(new RulerService(new LinkedHashSet<>()),
      fileParser, mapper);
  }

  private Ruler run(String fileName) {

    tameOfThrones.parseDetails(detailsPath, filePath + fileName);
    return tameOfThrones.run();
  }

  @Test
  void subjectsAreGreaterThanSubjectsRequired() {
    String fileName = "input1.txt";
    ruler = run(fileName);
    if (ruler.getSubjects() != null) {
      List<KingdomDto> subjects = ruler.getSubjects().stream()
          .collect(Collectors.toList());
      assertEquals("SPACE", ruler.getKingdomName());
      assertEquals(3, ruler.getSubjects().size());
      assertEquals("AIR", subjects.get(0).getKingdomName());
      assertEquals("LAND", subjects.get(1).getKingdomName());
      assertEquals("ICE", subjects.get(2).getKingdomName());
    }
  }

  @Test
  void subjectsAreLessThanSubjectsRequired() {
    String fileName = "input2.txt";
    ruler = run(fileName);    
    if (ruler.getSubjects() != null) {
      assertEquals("SPACE", ruler.getKingdomName());
      assertEquals(null, ruler.getSubjects());
    }
  }

  @Test
  void inputFileIsEmptyOrNotValid() {
    String fileName = "input0.txt";
    ruler = run(fileName);    
    
    assertEquals("SPACE", ruler.getKingdomName());
    assertEquals(null, ruler.getSubjects());
  }
}