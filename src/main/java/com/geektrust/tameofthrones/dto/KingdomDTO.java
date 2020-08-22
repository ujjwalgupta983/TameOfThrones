package com.geektrust.tameofthrones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KingdomDto {

  /**
   * KingdomDto is used to provide domain to each object.
   * 
   * @param kingdomName the name of kingdom 
   * @param emblem represents the emblem of the kingdom
   * @param message holds the encrypted message of the kingdom
   */

  @NonNull String kingdomName;
  @NonNull String emblem;
  @NonNull String message;

}