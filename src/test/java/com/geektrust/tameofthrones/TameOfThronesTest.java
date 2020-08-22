package com.geektrust.tameofthrones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.geektrust.tameofthrones.dto.KingdomDto;
import com.geektrust.tameofthrones.models.Ruler;
import com.geektrust.tameofthrones.services.RulerService;
import com.geektrust.tameofthrones.utils.FileParser;
import com.geektrust.tameofthrones.utils.Mapper;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  private final String filePath = "inputs/";
  private final String detailsPath = "fixtures/KingdomDetails.txt";

  @BeforeEach
  void setup() throws Exception {
    FileParser fileParser = new FileParser(new HashMap<>(), new ArrayList<>());
    Mapper mapper = new Mapper(new ArrayList<>(), new Ruler());
    tameOfThrones = new TameOfThrones(new RulerService(new LinkedHashSet<>()),
      fileParser, mapper);
  }

  private String getResourcePath(String filePath) throws URISyntaxException {
    URL url = this.getClass().getClassLoader().getResource(filePath);
    Path path = Paths.get(url.toURI());
    return path.toString();
  }

  private Ruler run(String fileName) throws URISyntaxException {
    String detailPath = getResourcePath(detailsPath);
    String inputPath = getResourcePath(filePath + fileName);
    tameOfThrones.parseDetails(detailPath, inputPath);
    return tameOfThrones.run();
  }

  @Test
  void subjectsAreGreaterThanSubjectsRequired() throws URISyntaxException {
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
  void subjectsAreLessThanSubjectsRequired() throws URISyntaxException {
    String fileName = "input2.txt";
    ruler = run(fileName);    
    if (ruler.getSubjects() != null) {
      assertEquals("SPACE", ruler.getKingdomName());
      assertEquals(null, ruler.getSubjects());
    }
  }

  @Test
  void inputFileIsEmptyOrNotValid() throws URISyntaxException {
    String fileName = "input0.txt";
    ruler = run(fileName);    
    
    assertEquals("SPACE", ruler.getKingdomName());
    assertEquals(null, ruler.getSubjects());
  }
}