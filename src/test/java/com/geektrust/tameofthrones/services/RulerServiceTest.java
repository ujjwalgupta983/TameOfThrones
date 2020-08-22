package com.geektrust.tameofthrones.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.geektrust.tameofthrones.dto.KingdomDto;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.models.Ruler;
import com.geektrust.tameofthrones.utils.CaesarCipher;
import com.geektrust.tameofthrones.utils.FileParser;
import com.geektrust.tameofthrones.utils.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RulerServiceTest {
  
  private IRulerService rulerService;
  private FileParser fileParser;
  private Mapper objectMapper;

  private final String filePath = "src\\test\\resources\\inputs\\";
  private final String detailsPath = "src\\test\\resources\\fixtures\\KingdomDetails.txt";

  @BeforeEach
  void setup() throws Exception {
    fileParser = new FileParser(new HashMap<>(), new ArrayList<Kingdom>());
    objectMapper = new Mapper(new ArrayList<KingdomDto>(), new Ruler());
    rulerService = new RulerService(new LinkedHashSet<>());
  }

  private LinkedHashSet<KingdomDto> getSubjects(List<KingdomDto> kingdoms) {
    if (kingdoms == null) {
      return null;
    }
    return rulerService.getSubjects(kingdoms);
  }

  @Test
  void checkWhetherSubjectsAreValidOrNot() {
    
    LinkedHashSet<KingdomDto> subjects = getSubjects(loadKingdomsThatBelongsToRuler());
    if (subjects != null) {
      List<KingdomDto> subjectList = subjects.stream().collect(Collectors.toList());
      assertEquals(3, subjects.size());
      assertEquals("AIR", subjectList.get(0).getKingdomName());
      assertEquals("LAND", subjectList.get(1).getKingdomName());
    }
  }

  @Test
  void checkWhenNoSubjectsBelongsToRuler() {
    LinkedHashSet<KingdomDto> subjects = getSubjects(loadKingdomsThatNotBelongsToRuler());
    if (subjects != null) {
      assertEquals(0, subjects.size());
    }
  }

  private List<KingdomDto> loadKingdomsThatBelongsToRuler() {
    
    String inputFileName = "input1.txt";
    HashMap<String, String> kingdomDetails = fileParser
        .parseKingdomDetails(detailsPath);

    List<Kingdom> kingdomList = fileParser.parseInput(filePath + inputFileName);
    List<KingdomDto> kingdoms = objectMapper.mapSubjects(kingdomDetails, kingdomList);
    if (kingdoms == null) {
      return null;
    }
    CaesarCipher cipher = new CaesarCipher();
    for (KingdomDto kingdomDto : kingdoms) {
      String cipherText = kingdomDto.getMessage();
      kingdomDto.setMessage(cipher.decrypt(cipherText,
          kingdomDto.getEmblem().length()));
    }
    return kingdoms;
  }

  private List<KingdomDto> loadKingdomsThatNotBelongsToRuler() {
    
    String inputFileName = "input4.txt";
    HashMap<String, String> kingdomDetails = fileParser
        .parseKingdomDetails(detailsPath);

    List<Kingdom> kingdomList = fileParser.parseInput(filePath + inputFileName);

    List<KingdomDto> kingdoms = objectMapper.mapSubjects(kingdomDetails, kingdomList);
    if (kingdoms == null) {
      return null;
    }
    CaesarCipher cipher = new CaesarCipher();
    for (KingdomDto kingdomDto : kingdoms) {
      String cipherText = kingdomDto.getMessage();
      kingdomDto.setMessage(cipher.decrypt(cipherText,
          kingdomDto.getEmblem().length()));
    }
    return kingdoms;
  }
}