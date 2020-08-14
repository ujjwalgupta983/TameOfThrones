package com.geektrust.tameofthrones.utils;

import java.util.HashSet;

import com.geektrust.tameofthrones.models.Kingdom;

public class Output {
    /**
     * response for parsing the output
     * @param map contains the list of subjects 
     * @param ruler holds the name of the ruler
     * @param minimumSubjects number of subject needed to become the ruler
     * 
     */
    public static void displayResult(HashSet<Kingdom> map, Kingdom ruler, int minimumSubjects) {
        
        // check whether the subjects is greater then minimumSubjects pr not
        if (map.size() < minimumSubjects) {
            System.out.print("NONE");    
        }
        
        else {
            System.out.print(ruler.getKingdomName()+" ");
            for (Kingdom kings : map) {
                System.out.print(kings.getKingdomName()+" ");
            }
        }
    }
}