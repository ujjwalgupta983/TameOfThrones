package com.geektrust.tameofthrones.utils;

import com.geektrust.tameofthrones.models.Kingdom;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class FileParser {

  /**
   * FileParser class parses the details from files like Input Details, Kingdom Details.
   * @param kingdomDetails holds pair (kingdomName, emblem) by parsing KingdomDetails.txt file so
   *     if we want to increase kingdoms then add to that file
   * @param kingdoms holds the inputs of kingdoms like name and message
   */

  private HashMap<String, String> kingdomDetails;
  private List<Kingdom> kingdoms;

  public FileParser(HashMap<String, String> kingdomDetails, List<Kingdom> kingdoms) {
    this.kingdomDetails = kingdomDetails;
    this.kingdoms = kingdoms;
  }
  
  /**
   * parseKingdomDetails method parses the details of kingdoms like their
   *     name and the emblem associated with the kingdom.
   * @param filePath holds the path of file having kingdom details
   * @return HashMap kingdomDetails which holds the (kingdomName: emblem) pair
   */

  public HashMap<String, String> parseKingdomDetails(String filePath) {

    // read file path using Path class
    Path pathToFile = Paths.get(filePath);

    // read each line of file
    try (BufferedReader br = Files.newBufferedReader(pathToFile,
        StandardCharsets.US_ASCII)) {
      String line = br.readLine();
      while (line != null) {
        // split name and emblem
        String[] attributes = line.split(" ");
        kingdomDetails.put(attributes[0], attributes[1]);
        line = br.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return kingdomDetails;
  }

  /**
   * parseInput method parses the input file provided by the user.
   * @param filePath holds the input file path
   * @return {@link List} of {@link Kingdom} kingdoms which holds the Kingdom Objects
   */

  public List<Kingdom> parseInput(String filePath) {

    // read file path using Path class
    Path pathToFile = Paths.get(filePath);

    // read each line of file
    try (BufferedReader br = Files.newBufferedReader(pathToFile,
        StandardCharsets.US_ASCII)) {
      String line = br.readLine();
      while (line != null) {
        if (line.equals("")) {
          break;
        }
        // split name and message
        String[] attributes = line.split(" ");

        // extract object of kingdom by passing attributes
        Kingdom kingdom = createKingdom(attributes);

        // add kingdom to the list
        kingdoms.add(kingdom);
        line = br.readLine();
      }
    } catch (final IOException e) {
      System.out.print("");
    } finally {    
      // check if file has no kingdoms
      if (kingdoms.size() == 0) {
        kingdoms.add(new Kingdom("NONE","NONE"));
      }
    }
    return kingdoms;
  }

  /**
   * createKingdom parse attributes into Kingdom object.
   * @param metadata holds the attributes of the kingdom
   */

  private static Kingdom createKingdom(String[] metadata) {

    // read kingdomName from input
    String kingdomName = metadata[0];

    int iterator = 1;
    String message = "";

    // read the encrypted message from the input file and adding to the Kingdom object
    // checks whether the attributes are not null
    while (iterator != metadata.length) {
      message = message + metadata[iterator];
      iterator++;
    }

    return new Kingdom(kingdomName, message);
  }
  
}