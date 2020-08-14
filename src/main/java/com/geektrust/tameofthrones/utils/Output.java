package com.geektrust.tameofthrones.utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.geektrust.tameofthrones.models.Kingdom;

public class Output {
    /**
     * response for parsing the output
     * @param map contains the list of subjects 
     * @param ruler holds the name of the ruler
     * @param minimumSubjects number of subject needed to become the ruler
     * @return list of output
     */

     private static List<String> outputList = new ArrayList<>();

    public static List<String> displayResult(LinkedHashSet<Kingdom> map, Kingdom ruler, int minimumSubjects) {
        
        // check whether the subjects is greater then minimumSubjects pr not
        if (map.size() < minimumSubjects) {
            System.out.print("NONE");    
        }

        else {
            outputList.add(ruler.getKingdomName()+" ");
            System.out.print(ruler.getKingdomName()+" ");
            for (Kingdom kings : map) {
                outputList.add(kings.getKingdomName()+" ");
                System.out.print(kings.getKingdomName()+" ");
            }
        }
        return outputList;
    }
}