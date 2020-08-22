package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.dto.KingdomDto;
import com.geektrust.tameofthrones.globals.GlobalConstants;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.models.Ruler;
import com.geektrust.tameofthrones.services.IRulerService;
import com.geektrust.tameofthrones.services.RulerService;
import com.geektrust.tameofthrones.utils.CaesarCipher;
import com.geektrust.tameofthrones.utils.FileParser;
import com.geektrust.tameofthrones.utils.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class TameOfThrones {

  /**
   * TameOfThrones class is the main class for this application.
   * 
   * @param rulerService is the object for calling RulerService class
   * @param kingdoms holds the List of KingdomDto which holds the (kingdomName, emblem, message)
   * @param ruler is the Ruler class object that includes(kingdomName, emblem)
   * @param fileParser is used to initialize FileParser Class
   * @param mapper holds the Mapper class objects which provides a mapping
   *     between Kingdom and KingdomDto and kingdomDetails and Ruler
   */ 

  private IRulerService rulerService;
  private Ruler ruler;
  private FileParser fileParser;
  private List<KingdomDto> kingdoms;
  private Mapper mapper;
    
  public TameOfThrones(IRulerService rulerService, FileParser fileParser,
      Mapper mapper) {
    this.rulerService = rulerService;
    this.fileParser = fileParser;
    this.mapper = mapper;
  }
    
  public static void main(String[] args) {
    FileParser fileParser = new FileParser(new HashMap<>(), new ArrayList<Kingdom>());
    Mapper mapper = new Mapper(new ArrayList<KingdomDto>(), new Ruler());
    TameOfThrones tameOfThrones = new TameOfThrones(
        new RulerService(new LinkedHashSet<>()), fileParser, mapper);
    
    // method for parsing kingdomDetails and inputDetails
    tameOfThrones.parseDetails(GlobalConstants.KINGDOM_DETAILS, args[0]);

    // method to preform business logic
    tameOfThrones.run();
    
    // method to print output
    tameOfThrones.printOutput();
  }

  // this method gets the path of resources using classpath
  
  /**
   * parseDetails method parses and maps details from input and kingdomDetails files.
   * @param detailsPath the path of the kingdomDetails file
   * @param inputPath the path of the input file
   */

  public void parseDetails(String detailsPath, String inputPath) {
    
    // parses KingdomDetails and stores in HashMap as (kingdomName: emblem)
    HashMap<String, String> kingdomDetails = fileParser.parseKingdomDetails(detailsPath); 

    // parses inputDetails and stores in List<Kingdom> as (kingdomName, message)
    List<Kingdom> kingdomList = fileParser.parseInput(inputPath);

    // maps details and inputs into List<KingdomDto> as (kingdomName, emblem, message)
    kingdoms = mapper.mapSubjects(kingdomDetails, kingdomList);

    // maps details and RulerKingdom to Ruler as (kingdomName, emblem)
    ruler = mapper.mapRuler(kingdomDetails, GlobalConstants.RULER_KINGDOM);

  }

  /**
   * run methods calls the business logic of the application.
   * @return Ruler object for unit testing
   */

  public Ruler run() {

    if (kingdoms == null) {
      ruler.setSubjects(null);
      return ruler;
    }
    // cipher object for decrypting messages of kingdoms
    CaesarCipher cipher = new CaesarCipher();
    for (KingdomDto kingdom : kingdoms) {
      String message = kingdom.getMessage();
      message = cipher.decrypt(message, kingdom.getEmblem().length());
      kingdom.setMessage(message);
    }

    // retrieves subjects of Ruler and stores into LinkedHashSet<KingdomDto>
    LinkedHashSet<KingdomDto> subjects = rulerService.getSubjects(kingdoms);

    // checks whether subjects are greater than minimum subjects to became a Ruler
    if (subjects.size() < GlobalConstants.MINIMUM_SUBJECTS) {
      ruler.setSubjects(null);
    } else {
      ruler.setSubjects(subjects);
    }
    return ruler; 
  }

  // printOutput method prints the output for the application
  public void printOutput() {
    
    // fetches subject details from Ruler class object
    if (ruler.getSubjects() == null) {
      System.out.print("NONE");
    } else {
      System.out.print(ruler.getKingdomName() + ' ');
      for (KingdomDto kingdoms : ruler.getSubjects()) {
        System.out.print(kingdoms.getKingdomName() + ' ');
      }
    }
  }
}
