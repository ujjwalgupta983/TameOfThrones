package com.geektrust.tameofthrones.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


public class ParseKingdomDetails {
    /**
     * ParseKingdomDetails is used the parse the data provided for Kingdoms like there Emblem
     * @param fileName 
     * @return map of KingdomName: emblem
     */

    private static HashMap<String, String> map = null;

    public static HashMap<String, String> getKingdomDetails(String fileName) {
        map = new HashMap<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
            StandardCharsets.US_ASCII)) {
                String line = br.readLine();
                while (line != null) {
                    String[] attributes = line.split(" ");
                    map.put(attributes[0], attributes[1]);
                    line = br.readLine();
                }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}