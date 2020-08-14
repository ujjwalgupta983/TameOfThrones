package com.geektrust.tameofthrones.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.geektrust.tameofthrones.dto.KingdomDTO;

public class InputParser {
    /**
     * get the data from input file and returns as a Kingdom's
     * @param fileName
     * @return list of list of Kingdom's DTO
     */
    public static List<KingdomDTO> getData(final String fileName) {

        final List<KingdomDTO> kingdoms = new ArrayList<>();

        final Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
            StandardCharsets.US_ASCII)) {
                String line = br.readLine();
                while (line != null) {
                    final String[] attributes = line.split(" ");
                    final KingdomDTO kingdom = createKingdom(attributes);
                    kingdoms.add(kingdom);
                    line = br.readLine();
                }
        } catch(final IOException e) {
            System.out.print("");
        } finally {
            if (kingdoms.size() == 0) {
                kingdoms.add(new KingdomDTO("NONE","NONE"));
            }
            return kingdoms;
        }
    }

    /**
     * create object of kingdom
     * @param metadata
     * @return
     */

    private static KingdomDTO createKingdom(final String[] metadata) {
        final String kingdomName = metadata[0];
        final String message = metadata[1];
        return new KingdomDTO(kingdomName, message);
    }
}