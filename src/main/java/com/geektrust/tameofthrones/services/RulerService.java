package com.geektrust.tameofthrones.services;

import com.geektrust.tameofthrones.dto.KingdomDTO;
import com.geektrust.tameofthrones.models.Kingdom;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface RulerService {
    /**
     * RulerService Interface provides abstraction for the RulerServiceImpl
     *
     */

    
     List<Kingdom> getKingdoms(List<KingdomDTO> kingdoms, HashMap<String, String> map, String rulerKingdom);

     List<Kingdom> decryptMsg(List<Kingdom> kingdomList);

     HashSet<Kingdom> getSubjects(List<Kingdom> kingdomList);

}