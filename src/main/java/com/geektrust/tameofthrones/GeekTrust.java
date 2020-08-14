package com.geektrust.tameofthrones;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.geektrust.tameofthrones.dto.KingdomDTO;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.repositories.KingdomRepoImpl;
import com.geektrust.tameofthrones.services.RulerService;
import com.geektrust.tameofthrones.services.RulerServiceImpl;
import com.geektrust.tameofthrones.utils.InputParser;
import com.geektrust.tameofthrones.utils.Output;
import com.geektrust.tameofthrones.utils.ParseKingdomDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeekTrust {

	/**
     * Main java file
     * @param args holds command line argument used to provide input file
     * @param rulerService used to call rulerServiceImpl class
     * @param kingdoms holds input kingdoms details
     * @param map holds details for each kingdoms
     * @param kingdomDetails holds data from rulerService
     * @param kings holds subjects of ruler
     */

    @Autowired
    private static RulerService rulerService;
    
    private static List<KingdomDTO> kingdoms;
    private static List<Kingdom> kingdomDetails;
    private static HashMap<String, String> map;
    private static HashSet<Kingdom> kings;
    private static final String kingdomsFilePath = "src/main/resources/fixtures/";
    private static final String RULER = "SPACE"; 
    private static final String kingdomsFileName = "KingdomDetails.txt";
    private static final int MINIMUMSUBJECTS = 3;

    public static void main(String[] args) {
        // parsing input
        kingdoms = InputParser
            .getData(args[0]);

        // parsing KingdomDetails
        map = ParseKingdomDetails
            .getKingdomDetails(kingdomsFilePath + kingdomsFileName);

        // call rulerService by passing object of KingdomRepoImpl     
        rulerService = new RulerServiceImpl(new KingdomRepoImpl());
        // get kingdom Details
        kingdomDetails = rulerService.getKingdoms(kingdoms, map, RULER);
        // decrypt message of each kingdoms
        kingdomDetails = rulerService.decryptMsg(kingdomDetails);
        // get subjects of each kingdoms
        kings = rulerService.getSubjects(kingdomDetails);
        Kingdom rulerKingdom = new Kingdom();
        // check for rulerKingdom
        for (Kingdom ruler : kingdomDetails) {
            if (ruler.isRuler()){
                rulerKingdom = ruler;
            }
        }
        // Print output by calling output class
        Output.displayResult(kings, rulerKingdom, MINIMUMSUBJECTS);
    }


}
