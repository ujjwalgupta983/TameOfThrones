package com.geektrust.tameofthrones.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.geektrust.tameofthrones.dto.KingdomDTO;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.repositories.KingdomRepo;
import com.geektrust.tameofthrones.utils.CaesarCipher;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RulerServiceImpl implements RulerService {

    /**
     * RulerServiceImpl implements the methods of RulerService interface
     * @param kingdomRepo is the service object that is used to call the method of kingdomRepo
     * @param kingdomList holds the data returned from kingdomRepo
     * @param subjects holds the subjects which are subjects of ruler
     * @param cipher is the object of CaesarCipher class
     * 
     */

    @Autowired
    private KingdomRepo kingdomRepo;

    private List<Kingdom> kingdomList;
    private HashSet<Kingdom> subjects;
    private CaesarCipher cipher;

    // Dependency Injection Constructor
    public RulerServiceImpl(KingdomRepo kingdomRepoImpl) {
        this.kingdomRepo = kingdomRepoImpl;
        cipher = new CaesarCipher();
        subjects = new HashSet<>();
    }

    /**
     * The method is responsible for fetch the list of Kingdoms with there details like (emblem, message, isRuler)
     * @param kingdoms holds the list of kingdom's parsed from inputs
     * @param map holds the key and value as (kingdomName, emblem)
     * @param rulerKingdom whether the kingdom is ruler or subject
     * @return kingdomList
     * 
     */

    @Override
    public List<Kingdom> getKingdoms(List<KingdomDTO> kingdoms,
             HashMap<String, String> map, String rulerKingdom) {
        kingdomList = kingdomRepo.getKingdomDetails(kingdoms, map, rulerKingdom);
        return kingdomList;
    }

    /**
     * decrypt the message for each kingdom associated with input
     * @param kindomList list of kingdoms that are to be decrypted
     * @return the list of decrypted kingdoms
     */
    @Override
    public List<Kingdom> decryptMsg(List<Kingdom> kingdomList) {
       for (Kingdom kingdom : kingdomList) {
           String decryptMsg = cipher.decrypt(kingdom.getMessage(), kingdom.getEmblem().length());
           kingdom.setMessage(decryptMsg);
       }
       return kingdomList;
    }

    /**
     * get details of subjects associated with ruler
     * @param kingdomList holds data of kingdoms parsed from kingdomDetails and decryptMsg
     * @return a set of kingdoms
     */
    @Override
    public HashSet<Kingdom> getSubjects(List<Kingdom> kingdomList) {  
        
        // @param flag to check whether the emblem is in message or not
        boolean flag = true;
        
        //iterate through each kingdoms
        for (Kingdom kingdom : kingdomList) {

            // if subjects is already added to hashset then continue
            if (subjects.contains(kingdom)) {
                continue;
            }

            String message = kingdom.getMessage();
            String emblem = kingdom.getEmblem().toUpperCase();
            int iter = 0;
            // checks whether the message contains emblem or not
            while(iter < emblem.length()) {
                // check each character of emblem if exists else 
                if (message.indexOf(emblem.charAt(iter)) != -1) {
                    // replace first occurrence of character by #
                    message = message.replaceFirst(Character.toString(emblem.charAt(iter)), "#");
                    iter++;
                }
                // else character not found
                else {
                    flag = false;
                    break;
                }
            }
            // add subject to hashset
            if (flag) {
                subjects.add(kingdom);
            }
            flag = true;
        }
        return subjects;
    }


}