package com.geektrust.tameofthrones.repositories;

import java.util.HashMap;
import java.util.List;

import com.geektrust.tameofthrones.dto.KingdomDTO;
import com.geektrust.tameofthrones.models.Kingdom;

public interface KingdomRepo {
    
    /**
     * KingdomRepo - To get the details of every kingdoms associated with the input
     * 
     * @param kingdom will pe parsed to Kingdom model and can we extended
     * @param map contains emblems of each kingdom 
     * @param rulerKingdom contains the information of ruler
     * @return the list of all kingdoms
     */

    List<Kingdom> getKingdomDetails(List<KingdomDTO> kingdom, HashMap<String,
         String> map, String rulerKingdom);

}