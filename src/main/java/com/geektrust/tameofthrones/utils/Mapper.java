package com.geektrust.tameofthrones.utils;

import com.geektrust.tameofthrones.dto.KingdomDto;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.models.Ruler;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mapper {
  
  /**
   * Mapper class binds kingdom details and kingdom inputs with KingdomDto.
   * @param kingdomList holds the list of KingdomDto
   * @param ruler holds the details of Ruler
   */

  private List<KingdomDto> kingdomList;
  private Ruler ruler;

  /**
   * mapSubjects method provides a mapping of kingdom details and kingdom inputs.
   * @param kingdomDetails holds the kingdom details
   * @param kingdoms holds the kingdom inputs
   * @return {@link List} of {@link KingdomDto}  kingdomList
   */

  public List<KingdomDto> mapSubjects(HashMap<String, String> kingdomDetails,
      List<Kingdom> kingdoms) {
    
    if (kingdoms.get(0).getKingdomName().equals("NONE")) {
      return null;
    }
    for (Kingdom kingdom : kingdoms) {
      String emblem = kingdomDetails.get(kingdom.getKingdomName());
      kingdomList.add(new KingdomDto(kingdom.getKingdomName(), emblem, kingdom.getMessage()));
    }
    return kingdomList;
  }

  /**
   * mapRuler binds the kingdom as Ruler Kingdom.
   * @param kingdomDetails holds the details of the kingdom
   * @param rulerName holds the name of Ruler Kingdom
   * @return {@link Ruler} class object
   */

  public Ruler mapRuler(HashMap<String, String> kingdomDetails, String rulerName) {

    ruler = new Ruler(rulerName, kingdomDetails.get(rulerName), new LinkedHashSet<>());
    return ruler;
  }
}