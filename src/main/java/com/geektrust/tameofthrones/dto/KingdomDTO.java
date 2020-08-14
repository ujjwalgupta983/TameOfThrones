package com.geektrust.tameofthrones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KingdomDTO {
    /**
     * KingdomDTO is used to provide domain to each object
     * 
     * @param kingdomName the name of kingdom 
     * @param message holds the encrypted message of the kingdom
     *  
     */

     @NonNull String kingdomName;
     @NonNull String message;

}