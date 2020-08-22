package com.geektrust.tameofthrones.services;

import com.geektrust.tameofthrones.dto.KingdomDto;

import java.util.LinkedHashSet;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RulerService implements IRulerService {

  private LinkedHashSet<KingdomDto> subjects;

  @Override
  public LinkedHashSet<KingdomDto> getSubjects(List<KingdomDto> kingdomList) {
    
    boolean flag = true;
    // iterate through each kingdoms
    for (KingdomDto kingdom : kingdomList) {

      // if subjects is already added to set then continue
      if (subjects.contains(kingdom)) {
        continue;
      }
      String message = kingdom.getMessage();
      String emblem = kingdom.getEmblem().toUpperCase();
      int iter = 0;

      // checks whether the message contains emblem or not
      while (iter < emblem.length()) {

        // check each character of emblem if exists else 
        if (message.indexOf(emblem.charAt(iter)) != -1) {        
          // replace first occurrence of character by #
          message = message.replaceFirst(Character.toString(emblem.charAt(iter)), "#");
          iter++;
        } else {
          flag = false;
          break;
        }
      }

      // add subject to set
      if (flag) {
        subjects.add(kingdom);
      }
      
      flag = true;
    }
    return subjects;
  }
  
}