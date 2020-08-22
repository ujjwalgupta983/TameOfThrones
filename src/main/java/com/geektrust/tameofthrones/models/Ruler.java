package com.geektrust.tameofthrones.models;

import com.geektrust.tameofthrones.dto.KingdomDto;

import java.util.LinkedHashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruler {
  
  /**
   * Ruler Class represents the Ruler of the Kingdom.
   * 
   * @param kingdomName holds the name of the Kingdom
   * @param emblem holds the emblem of the Kingdom
   * @param subjects holds the set of subjects;
   */
  
  @NonNull String kingdomName;
  @NonNull String emblem;
  LinkedHashSet<KingdomDto> subjects;
}