package com.geektrust.tameofthrones.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.geektrust.tameofthrones.dto.KingdomDTO;
import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.repositories.KingdomRepo;
import com.geektrust.tameofthrones.repositories.KingdomRepoImpl;
import com.geektrust.tameofthrones.utils.InputParser;
import com.geektrust.tameofthrones.utils.ParseKingdomDetails;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

public class RulerServiceTest {

    /**
     * RulerServiceTest is created to test the RulerService Class
     * @param rulerService used to call RulerService Methods
     * @param kingdomsList get data from ruler service
     * @param map used to fetch kingdomDetails
     * @return subjects  
     */
    @InjectMocks
    private RulerService rulerService;

    private List<KingdomDTO> kingdoms;
    private List<Kingdom> kingdomList;
    private HashSet<Kingdom> subjects;
    private HashMap<String, String> map;
    private final String inputFilePath = "src/test/resources/inputs/";
    private final String kingdomsFilePath = "src/test/resources/fixtures/";
    private final String inputFileName = "input1.txt";
    private final String kingdomsFileName = "KingdomDetails.txt";
    private final String rulerKingdom = "SPACE";

    public List<Kingdom> setup() {
        // parse input
        kingdoms = InputParser.getData(inputFilePath + inputFileName);
        // parse kingdom Details
        map = ParseKingdomDetails.getKingdomDetails(kingdomsFilePath + kingdomsFileName);
        rulerService = new RulerServiceImpl(new KingdomRepoImpl());
        kingdomList = rulerService.getKingdoms(kingdoms, map, rulerKingdom);
        assertEquals("AIR", kingdomList.get(0).getKingdomName());
        kingdomList = rulerService.decryptMsg(kingdomList);
        assertEquals("OLWL",kingdomList.get(0).getMessage());
        return kingdomList;
    }

    @Test
    public HashSet<Kingdom> getSubjects(List<Kingdom> kingdomList) {       
        kingdomList = this.kingdomList;
        subjects = rulerService.getSubjects(kingdomList);
        assertEquals(3, subjects.size());
        return subjects;
    }
}