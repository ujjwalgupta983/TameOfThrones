package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class ParseKingdomDetailsTest {
    
    /**
     * ParseKingdomDetails is used the parse the data provided for Kingdoms like there Emblem
     * @param fileName 
     * @return the map of KingdomName: emblem
     */

    private static HashMap<String, String> map = null;
    private final static String filePath = "src/test/resources/fixtures/";
    private final static String fileName = "KingdomDetails.txt";

    public static HashMap<String, String> getKingdomDetails(String fileName) {
        map = new HashMap<>();
        fileName = ParseKingdomDetailsTest.fileName;
        map = ParseKingdomDetails.getKingdomDetails(filePath + fileName);
        assertEquals(6, map.size());
        assertEquals("Gorilla", map.get("SPACE"));
        return map;
    }
}