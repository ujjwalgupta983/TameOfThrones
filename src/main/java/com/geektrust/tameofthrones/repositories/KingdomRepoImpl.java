package com.geektrust.tameofthrones.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geektrust.tameofthrones.dto.KingdomDTO;
import com.geektrust.tameofthrones.models.Kingdom;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class KingdomRepoImpl implements KingdomRepo {

    /**
     * KingdomRepo is created to get the details the every kingdoms associated with the input
     * 
     * @param kingdom will pe parsed to Kingdom model and can we further extended
     * @param map contains emblems of each kingdom 
     * @param rulerKingdom contains the information of ruler
     * @return kingdoms the list of all kingdoms associated in Hashmap
     */

     private List<Kingdom> kingdoms = null;

    @Override
    public List<Kingdom> getKingdomDetails(List<KingdomDTO> kingdom, HashMap<String,
            String> map, String rulerKingdom) {
        kingdoms = new ArrayList<>();

        // iterate each kingdom
        for(KingdomDTO iter : kingdom) {

            //Check whether the kingdom is valid or not
            if (map.containsKey(iter.getKingdomName()) == false) {
                continue;
            }

            String emblem = map.get(iter.getKingdomName());
            // initialize new kingdom with their details (name, emblem, message, isRuler)
            Kingdom newKingdom = new Kingdom(iter.getKingdomName(), emblem, iter.getMessage(), false);

            // add kingdom to list
            kingdoms.add(newKingdom);

        }

        // add ruler to the list
        for (Map.Entry<String, String> entry : map.entrySet())  {
            String  rule = entry.getKey().toString();
            boolean isRuler = rule.equals(rulerKingdom) ? true : false;
            if (isRuler) {
                kingdoms.add(new Kingdom(entry.getKey(), entry.getValue(), "", isRuler));
            }
        } 
        
        return kingdoms;
    }


}
    