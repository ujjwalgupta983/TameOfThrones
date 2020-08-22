package com.geektrust.tameofthrones.services;

import com.geektrust.tameofthrones.dto.KingdomDto;

import java.util.LinkedHashSet;
import java.util.List;

public interface IRulerService {
  
  /**
   * IRulerService provides interface for RulerService class.
   */

  LinkedHashSet<KingdomDto> getSubjects(List<KingdomDto> kingdomList);
}