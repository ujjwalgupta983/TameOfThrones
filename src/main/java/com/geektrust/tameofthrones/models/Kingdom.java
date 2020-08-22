package com.geektrust.tameofthrones.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kingdom {

  /**
   * This class provide the Entity as a Kingdom.
   * 
   * @param kingdomName holds the name of the kingdom
   * @param emblem holds the emblem of each kingdom
   * @param message holds the message which is received by the kingdoms 
   */

  @NonNull String kingdomName;
  @NonNull String message;
}

