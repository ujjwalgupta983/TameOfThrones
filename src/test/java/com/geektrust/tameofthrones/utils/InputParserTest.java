package com.geektrust.tameofthrones.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.geektrust.tameofthrones.dto.KingdomDTO;

import org.junit.jupiter.api.Test;

public class InputParserTest {
    
    /**
     * get the data from input file and returns as a Kingdom's
     * @param fileName
     * @return list of list of Kingdom's DTO
     */
    
    private final String filePath = "src/test/resources/inputs/";
    private final String fileName = "input1.txt";
    private final String emptyFileName = "input0.txt";

    @Test
    public List<KingdomDTO> getData(String fileName) {
        fileName = this.fileName;
        final List<KingdomDTO> kingdoms = InputParser.getData(this.filePath + fileName);
        assertEquals("AIR", kingdoms.get(0).getKingdomName());
        assertEquals("LAND", kingdoms.get(1).getKingdomName());
        return kingdoms;
    }

    @Test
    public List<KingdomDTO> getEmptyData(String emptyFileName) {
        emptyFileName = this.emptyFileName;
        final List<KingdomDTO> kingdoms = InputParser.getData(this.filePath + emptyFileName);
        assertEquals("NONE", kingdoms.get(0).getKingdomName());
        return kingdoms;
    }
}